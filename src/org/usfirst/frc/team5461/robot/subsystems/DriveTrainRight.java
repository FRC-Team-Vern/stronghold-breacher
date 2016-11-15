package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DriveTrainRight extends PIDRateSubsystem {
	private CANTalon front_right_motor, back_right_motor;
    // Initialize your subsystem here
    public DriveTrainRight() {
    	super(DriveTrainContract.kP_real, DriveTrainContract.kI_real, DriveTrainContract.kD_real, .05, DriveTrainContract.kF_real);
		setPercentTolerance(DriveTrainContract.k_tolPercent);
		setOutputRange(DriveTrainContract.k_ouptutMin, DriveTrainContract.k_outputMax);
		
		//Front motor
		front_right_motor = new CANTalon(9);

		front_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_right_motor.configEncoderCodesPerRev(128);
		front_right_motor.setEncPosition(0);
		
		//Back motor
		back_right_motor = new CANTalon(10);
//		back_right_motor.setInverted(true);
//		back_right_motor.setExpiration(0.1);
		back_right_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_right_motor.configEncoderCodesPerRev(128);
    	
		setInputRange(DriveTrainContract.k_inputMin,DriveTrainContract.k_inputMax);
		//enable();

    }
    public void reset() {
		back_right_motor.setEncPosition(0);
		front_right_motor.setEncPosition(0);
	}
    public double getDistance() {
		return back_right_motor.getEncPosition();
	}
    public void log() {	
		SmartDashboard.putNumber("Right Back Temp", back_right_motor.getTemperature());
		SmartDashboard.putNumber("Right Front Temp", front_right_motor.getTemperature());
		SmartDashboard.putNumber("Front Right Distance", front_right_motor.getEncPosition());
		
		//SmartDashboard.putData("Drive Train PID", getPIDController());
	}
    public void drive(double right) {

		FlatIron.Pair<Double>adjustFactors=Robot.flatIron.getAdjustmentFactors();
		double right_results=right*adjustFactors.m_rightval;
		//setSetpoint(right);
    	usePIDOutput(right_results);
    	//System.out.println("Right Set: " + Double.toString(getPIDController().getSetpoint()));
		//SmartDashboard.putNumber("PID Result 0", m_results.get(0));
		//SmartDashboard.putNumber("PID Result 1", m_results.get(1));
	}
    
    public void drive(Joystick joy) {
    	Double rightVal=-joy.getRawAxis(Joystick.AxisType.kTwist.value);
    	if (Robot.flatIron.getIsEnabled()) {
    		rightVal = -joy.getRawAxis(Joystick.AxisType.kY.value);
    	}
		rightVal=applyDeadband(rightVal);		
		drive(rightVal);
	}
    
    private Double applyDeadband(Double val) {
		
    	if(Math.abs(val)<0.1){
    		return new Double (0.0);
    	}
    	return val;
	}

    public void initDefaultCommand() {
    	// Default TankDriveWithJoystick command is in DriveTrainLeft
    }
    
    protected double returnPIDInput() {
    	return getAverageSpeed(front_right_motor, back_right_motor);
    }
    
    protected void usePIDOutput(double output) {
    	front_right_motor.set(output);
    	back_right_motor.set(output);
    	//System.out.println("Output Right: " + Double.toString(output));

    }
    
    protected double getAverageSpeed(CANTalon first, CANTalon second) {
		double avgSpeed = (double)(((double)(first.getEncVelocity() + second.getEncVelocity()) * 0.5) / DriveTrainContract.k_inputMax);
		//System.out.println("Avg Speed Right: " + avgSpeed);
		return avgSpeed;
	}
}
