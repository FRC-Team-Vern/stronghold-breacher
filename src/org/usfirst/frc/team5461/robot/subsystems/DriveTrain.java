package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends PIDSubsystem {
	private CANTalon front_left_motor, back_left_motor,
							front_right_motor, back_right_motor;
	private RobotDrive drive;
	//private AnalogInput rangefinder;
	private ADIS16448_IMU imu;
	private FlatIron flatIron;
	private static final double kP_real = .5, kI_real = 0.00;
	
	public DriveTrain() {
		super(kP_real,kI_real,0);
		setPercentTolerance(5.0);
		setOutputRange(-1.0,1.0);
		setInputRange(-40.0,40.0);
		front_left_motor = new CANTalon(8);
		front_left_motor.setInverted(true);
		front_left_motor.setExpiration(0.1);
		front_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_left_motor.configEncoderCodesPerRev(128);
		front_right_motor = new CANTalon(9);
		front_right_motor.setInverted(true);
		front_right_motor.setExpiration(0.1);
		front_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_right_motor.configEncoderCodesPerRev(128);
		back_left_motor = new CANTalon(11);
		back_left_motor.setInverted(true);
		back_left_motor.setExpiration(0.1);
		back_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_left_motor.configEncoderCodesPerRev(128);
		back_right_motor = new CANTalon(10);
		back_right_motor.setInverted(true);
		back_right_motor.setExpiration(0.1);
		back_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_right_motor.configEncoderCodesPerRev(128);

		drive = new RobotDrive(front_left_motor, back_left_motor,
							   front_right_motor, back_right_motor);

        imu = new ADIS16448_IMU();
		
		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		if (Robot.isReal()) {
			//left_encoder.setDistancePerPulse(0.042);
			//right_encoder.setDistancePerPulse(0.042);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			//left_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
			//right_encoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
		}

		//rangefinder = new AnalogInput(6);
		flatIron=new FlatIron( imu);

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", front_left_motor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor",  back_left_motor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", front_right_motor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", back_right_motor);
		//LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
		LiveWindow.addActuator("Drive Train PID", "PID", getPIDController());
	}

	/**
	 * When no other command is running let the operator drive around
	 * using the PS3 joystick.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Distance", back_left_motor.getEncPosition());
		SmartDashboard.putNumber("Right Distance", back_right_motor.getEncPosition());
		SmartDashboard.putNumber("Left Speed", back_left_motor.getEncVelocity());
		SmartDashboard.putNumber("Right Speed", back_right_motor.getEncVelocity());
		SmartDashboard.putNumber("Left Back Speed", back_left_motor.get());
		SmartDashboard.putNumber("Right Back Speed", back_right_motor.get());
		SmartDashboard.putNumber("Left Front Speed", front_left_motor.get());
		SmartDashboard.putNumber("Right Front Speed", front_right_motor.get());
		SmartDashboard.putNumber("Left Back Temp", back_left_motor.getTemperature());
		SmartDashboard.putNumber("Right Back Temp", back_right_motor.getTemperature());
		SmartDashboard.putNumber("Left Front Temp", front_left_motor.getTemperature());
		SmartDashboard.putNumber("Right Front Temp", front_right_motor.getTemperature());
		
		SmartDashboard.putData("Drive Train PID", getPIDController());
	}

	/**
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}

	/**
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick joy) {
		FlatIron.Pair<Double>adjustFactors=flatIron.getAdjustmentFactors();
		Double leftVal=-joy.getRawAxis(Joystick.AxisType.kY.value)*adjustFactors.m_leftval;
		Double rightVal=-joy.getRawAxis(Joystick.AxisType.kTwist.value)*adjustFactors.m_rightval;
		drive(leftVal,rightVal);
	}
	
	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return 0.0;
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		back_right_motor.reset();
		back_left_motor.reset();
		back_right_motor.setEncPosition(0);
		back_left_motor.setEncPosition(0);
		front_right_motor.setEncPosition(0);
		front_left_motor.setEncPosition(0);

	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (back_left_motor.getEncPosition() + back_right_motor.getEncPosition())/2;
	}
	
	/**
	 * @return The distance to the obstacle detected by the rangefinder. 
	 */
//	public double getDistanceToObstacle() {
//		// Really meters in simulation since it's a rangefinder...
//		return rangefinder.getAverageVoltage();
//	}
	
	public void stopRobot(){
		drive(0,0);
	}

	@Override
	protected double returnPIDInput() {
		return back_right_motor.getEncVelocity();
	}
	
	public double getImuZValue(){
		return imu.getAngleZ();
	}
	
	public double getImuXValue(){
		return imu.getAngleX();
	}
	
	public double getImuYValue(){
		return imu.getAngleY();
	}

	@Override
	protected void usePIDOutput(double output) {
		drive(output,output);
		
	}

//	public void setSetpoint(Joystick joystick) {
//		getPIDController().setSetpoint(joystick.);
//		
//	}
}
