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

import org.usfirst.frc.team5461.robot.MultiPIDSubsystem;
import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends MultiPIDSubsystem {
	private CANTalon front_left_motor, back_left_motor,
							front_right_motor, back_right_motor;
//	private RobotDrive drive;
	//private AnalogInput rangefinder;
	private final double SPEED_MAX = 350.0;
	

	private static final double kP_real = 0.0003;
	private static final double kI_real = 0.00;
	private static final double kD_real = 0.00;
	private static final double kF_real = 1.00;

	public DriveTrain() {
		super(kP_real,kI_real,kD_real);
		setPercentTolerance(5.0);
		setOutputRange(-1.0,1.0);
		front_left_motor = new CANTalon(8);
		front_left_motor.setInverted(true);
//		front_left_motor.setExpiration(0.1);
		front_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_left_motor.configEncoderCodesPerRev(128);
		front_right_motor = new CANTalon(9);
//		front_right_motor.setInverted(true);
//		front_right_motor.setExpiration(0.1);
		front_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_right_motor.configEncoderCodesPerRev(128);
		front_right_motor.setEncPosition(0);
		back_left_motor = new CANTalon(11);
		back_left_motor.setInverted(true);
//		back_left_motor.setExpiration(0.1);
		back_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_left_motor.configEncoderCodesPerRev(128);
		back_right_motor = new CANTalon(10);
//		back_right_motor.setInverted(true);
//		back_right_motor.setExpiration(0.1);
		back_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_right_motor.configEncoderCodesPerRev(128);

//		drive = new RobotDrive(front_left_motor, back_left_motor,
//							   front_right_motor, back_right_motor);
//		drive.setSafetyEnabled(false);
        
		
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
		
		
		setPIDSourceType(PIDSourceType.kRate);
		// First replace the original controller with a controller 
		//	that can accept a change of PIDSourceType
		addController(createNewDriveTrainPIDController(kP_real, kI_real, kD_real, kF_real, 0), 0);
		addController(createNewDriveTrainPIDController(kP_real, kI_real, kD_real, kF_real, 1), 1);
		enableControllers();
		setInputRange(-40.0,40.0);
		
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
		
		SmartDashboard.putNumber("Left Back Temp", back_left_motor.getTemperature());
		SmartDashboard.putNumber("Right Back Temp", back_right_motor.getTemperature());
		SmartDashboard.putNumber("Left Front Temp", front_left_motor.getTemperature());
		SmartDashboard.putNumber("Right Front Temp", front_right_motor.getTemperature());
		SmartDashboard.putNumber("Front Right Distance", front_right_motor.getEncPosition());
		
		//SmartDashboard.putData("Drive Train PID", getPIDController());
	}

	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		setSetPoint(left, 0);
		setSetPoint(right, 1);
		//SmartDashboard.putNumber("PID Setpoint 0", left);
		//SmartDashboard.putNumber("PID Setpoint 1", right);
		FlatIron.Pair<Double>adjustFactors=Robot.flatIron.getAdjustmentFactors();
		//SmartDashboard.putNumber("left adjustment",adjustFactors.m_leftval);
		//SmartDashboard.putNumber("right adjustment",adjustFactors.m_rightval);
		
		double left_results=m_results.get(0)*adjustFactors.m_leftval;
		double right_results=m_results.get(1)*adjustFactors.m_rightval;
//		drive.tankDrive(m_results.get(0), m_results.get(1));
		
		front_left_motor.set(left_results);
		back_left_motor.set(left_results);
		front_right_motor.set(right_results);
		back_right_motor.set(right_results);
		
		//SmartDashboard.putNumber("PID Result 0", m_results.get(0));
		//SmartDashboard.putNumber("PID Result 1", m_results.get(1));
	}

	/**
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick joy) {
		Double leftVal=-joy.getRawAxis(Joystick.AxisType.kY.value);
		Double rightVal=-joy.getRawAxis(Joystick.AxisType.kTwist.value);
		leftVal=applyDeadband(leftVal);
		rightVal=applyDeadband(rightVal);
		if (Robot.flatIron.getIsEnabled()){
			rightVal = leftVal;
		}
		drive(leftVal,rightVal);
	}
	
	private Double applyDeadband(Double val) {
		// TODO Auto-generated method stub
		
	if(Math.abs(val)<0.1){return new Double (0.0);}
	return val;
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
		back_right_motor.setEncPosition(0);
		back_left_motor.setEncPosition(0);
		front_right_motor.setEncPosition(0);
		front_left_motor.setEncPosition(0);
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return ((double)(-1*back_left_motor.getEncPosition() + back_right_motor.getEncPosition()))*0.5;
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
	protected void usePIDOutput(double output) {
		drive(output,output);
		
	}
	
	protected PIDController createNewDriveTrainPIDController(double p,
			double i,
			double d,
			double f, 
			int position) {
		
		PIDSource source = getNewPIDSource(position);
		PIDOutput output = getNewPIDOutput(position);
		
		return new PIDController(p, i, d, f, source, output);
	}

	@Override
	protected double returnPIDInput(int position) {
		switch(position) {
		case 0:  // Left side
			return -1.0*getAverageSpeed(back_left_motor, front_left_motor);
		case 1: // Right side
			return getAverageSpeed(back_right_motor, front_right_motor);
		default:	
		}
		return 0;
	}
	
//	protected void usePIDOutput(double output, int position) {
//		super.usePIDOutput(output, position);
//		if (position == 1) {
//			drive.tankDrive(m_results.get(0), m_results.get(1));
//			SmartDashboard.putNumber("PID Result 0", m_results.get(0));
//			SmartDashboard.putNumber("PID Result 1", m_results.get(1));
//		}
//	}
	
	protected double getAverageSpeed(CANTalon first, CANTalon second) {
		return (double)(((double)(first.getEncVelocity() + second.getEncVelocity()) * 0.5) / SPEED_MAX);
	}
}
