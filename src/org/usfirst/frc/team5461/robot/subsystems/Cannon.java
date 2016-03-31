package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Cannon extends PIDSubsystem {

	CANTalon cannonLiftMotor;
	private static final double cannonLiftMotorPower = 0.75;
	private static final int maxEncoderPosition = 500;
	private static final double kP_real = 0.005;
	private static final double kI_real = 0.00;
	private static final double kD_real = 0.00;
	private static final double kF_real = 1.00 / (double)maxEncoderPosition;

	private CannonPosition currentCannonPosition;
	
	public enum CannonPosition {
		Bottom,
		Middle,
		Top
	}
	
	public Cannon(){
		super(kP_real, kI_real, kD_real, PIDController.kDefaultPeriod, kF_real);
		setAbsoluteTolerance(50);
		setInputRange(0, maxEncoderPosition);
		cannonLiftMotor = new CANTalon(17);
		cannonLiftMotor.setExpiration(0.1);
		cannonLiftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		cannonLiftMotor.configEncoderCodesPerRev(497);

		currentCannonPosition = CannonPosition.Bottom;
		resetEncoder();
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void moveCannonUp() {
		cannonLiftMotor.set(cannonLiftMotorPower);
	}
	
	public void moveCannonDown() {
		cannonLiftMotor.set(-1.0*cannonLiftMotorPower);
	}
	
	public void stopCannon() {
		cannonLiftMotor.set(0);
	}
	
	private int getEncoderValue() {
		return -1*cannonLiftMotor.getEncPosition();
	}
	
	public void resetEncoder() {
		cannonLiftMotor.setEncPosition(0);
	}
	
	public CannonPosition getCurrentPosition() {
		return currentCannonPosition;
	}
	
	public void log() {
		SmartDashboard.putNumber("Cannon Encoder Value", getEncoderValue());
		SmartDashboard.putString("Cannon Position", currentCannonPosition.toString());
	}


	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getEncoderValue();
	}


	@Override
	protected void usePIDOutput(double output) {
		cannonLiftMotor.set(output);
		SmartDashboard.putNumber("Cannon output", output);
		
	}

}