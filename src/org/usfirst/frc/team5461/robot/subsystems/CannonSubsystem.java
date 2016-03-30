package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CannonSubsystem extends Subsystem {

	CANTalon cannonLiftMotor;
	CANTalon cannonMotor;
	Servo cannonTopPusher;
	Servo cannonBottomPusher;
	private static final double cannonMotorPower = 0.75;
	private static final double cannonLiftMotorPower = 0.75;
	private static final double bottomPusherIn = 0.5;
	private static final double bottomPusherOut = 1;
	private static final double topPusherIn = 0.5;
	private static final double topPusherOut = 0;
	private static final int bottomEncoderPosition = 0;
	private static final int middleEncoderPosition = 100;
	private static final int topEncoderPosition = 200;
	private CannonPosition currentCannonPosition;
	
	public enum CannonPosition {
		Bottom,
		Middle,
		Top
	}
	
	public CannonSubsystem(){
		cannonLiftMotor = new CANTalon(18);
		cannonLiftMotor.setExpiration(0.1);
		cannonMotor = new CANTalon(17);
		cannonMotor.setExpiration(0.1);
		cannonTopPusher = new Servo(0);
		cannonBottomPusher = new Servo(1);
		resetEncoder();
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void turnMotorIntoChassis() {
		cannonMotor.set(-cannonMotorPower);
	}
	
	public void turnMotorOutOfChassis() {
		cannonMotor.set(cannonMotorPower);
	}
	
	public void moveCannonUp() {
		cannonMotor.set(cannonLiftMotorPower);
	}
	
	public void moveCannonDown() {
		cannonMotor.set(-cannonLiftMotorPower);
	}
	
	public void stopCannonMotor() {
		cannonMotor.set(0);
	}
	
	public void stopLiftMotor() {
		cannonLiftMotor.set(0);
	}
	
	public boolean isAtBottomPosition() {
		currentCannonPosition = CannonPosition.Bottom;
		return cannonLiftMotor.getEncPosition() <= bottomEncoderPosition;
	}
	
	public boolean isAtMiddlePosition() {
		switch(currentCannonPosition) {
		case Top:
			currentCannonPosition = CannonPosition.Middle;
			return cannonLiftMotor.getEncPosition() <= middleEncoderPosition;
		case Middle:
			return true;
		case Bottom:
			currentCannonPosition = CannonPosition.Middle;
			return cannonLiftMotor.getEncPosition() >= middleEncoderPosition;
			default:
				return false;
		}
	}
	
	public boolean isAtTopPosition() {
		currentCannonPosition = CannonPosition.Top;
		return cannonLiftMotor.getEncPosition() >= topEncoderPosition;
	}
	
	public void moveServosIn() {
		cannonBottomPusher.set(bottomPusherIn);
		cannonTopPusher.set(topPusherIn);
	}
	
	public void resetEncoder() {
		cannonLiftMotor.setEncPosition(0);
	}
	
	public void moveServosOut() {
		cannonBottomPusher.set(bottomPusherOut);
		cannonTopPusher.set(topPusherOut);
	}
	
	public CannonPosition getCurrentPosition() {
		return currentCannonPosition;
	}

}