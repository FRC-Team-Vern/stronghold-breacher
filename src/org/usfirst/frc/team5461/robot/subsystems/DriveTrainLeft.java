package org.usfirst.frc.team5461.robot.subsystems;

import org.slf4j.Logger;
import org.usfirst.frc.team5461.robot.AveragingJoystick;
import org.usfirst.frc.team5461.robot.EventLogging;
import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.EventLogging.Level;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DriveTrainLeft extends PIDRateSubsystem {
	private CANTalon front_left_motor, back_left_motor;
	private double mJoystickInput;
	private double mFirstEncVelocity;
	private double mSecondEncVelocity;
	private double mAverageSpeed;
	private double mPIDOutput;
	 
	static Logger logger = EventLogging.getLogger(DriveTrainLeft.class, Level.INFO);
	
    // Initialize your subsystem here
    public DriveTrainLeft() {
    	super(DriveTrainContract.kP_real, DriveTrainContract.kI_real, DriveTrainContract.kD_real, .05, DriveTrainContract.kF_real);
		setPercentTolerance(DriveTrainContract.k_tolPercent);
		setOutputRange(DriveTrainContract.k_ouptutMin, DriveTrainContract.k_outputMax);
		
		//Front motor
		front_left_motor = new CANTalon(8);
		front_left_motor.setInverted(true);
		front_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_left_motor.configEncoderCodesPerRev(128);
		
		//Back motor
		back_left_motor = new CANTalon(11);
		back_left_motor.setInverted(true);
		back_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_left_motor.configEncoderCodesPerRev(128);
    
		setInputRange(DriveTrainContract.k_inputMin,DriveTrainContract.k_inputMax);
		enable();

    }
    public double getDistance() {
		return -1*back_left_motor.getEncPosition();
	}
    
    public void log() {
		logger.info(Double.toString(mJoystickInput) + "," +
				Double.toString(mFirstEncVelocity) + "," + 
				Double.toString(mSecondEncVelocity) + "," + 
				Double.toString(mAverageSpeed) + "," + 
				Double.toString(mPIDOutput));
	}
    
    public void reset() {
		back_left_motor.setEncPosition(0);
		front_left_motor.setEncPosition(0);
	}
    
    public void drive(double left) {
		FlatIron.Pair<Double> adjustFactors = Robot.flatIron.getAdjustmentFactors();
		double left_results = left*adjustFactors.m_leftval;
		mJoystickInput = left;
		setSetpoint(left_results);
	}
    
    public void drive(Joystick joy) {
		Double leftVal = -joy.getRawAxis(Joystick.AxisType.kY.value);
		leftVal = applyDeadband(leftVal);
		drive(leftVal);
	}
    
    private Double applyDeadband(Double val) {
    	if (Math.abs(val) < 0.1) {
    		return new Double (0.0);
    	}
    	return val;
	}

    public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
    }
    
    @Override
    protected double returnPIDInput() {
    	return getAverageSpeed(front_left_motor, back_left_motor);
    }
    
    @Override
    protected void usePIDOutput(double output) {
    	mPIDOutput = output;
    	front_left_motor.set(output);
    	back_left_motor.set(output);
    }
    
    protected double getAverageSpeed(CANTalon first, CANTalon second) {
    	mFirstEncVelocity = (double)first.getEncVelocity() / DriveTrainContract.k_inputMax;
    	mSecondEncVelocity = (double)first.getEncVelocity() / DriveTrainContract.k_inputMax;
		mAverageSpeed = ((mFirstEncVelocity + mSecondEncVelocity) * 0.5);
		return mAverageSpeed;
	}
}
