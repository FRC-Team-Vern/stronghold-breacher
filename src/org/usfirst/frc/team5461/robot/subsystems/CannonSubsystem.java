package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CannonSubsystem extends Subsystem {

	CANTalon cannonLiftMotor;
	private static final double cannonLiftMotorPower = 0.75;
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
		cannonLiftMotor.set(-cannonLiftMotorPower);
	}
	
	public void stopCannon() {
		cannonLiftMotor.set(0);
	}
	
	public boolean isAtBottomPosition() {
		if(cannonLiftMotor.getEncPosition() <= bottomEncoderPosition) {
			currentCannonPosition = CannonPosition.Bottom;
			return true;
		}
		return false;
	}
	
	public boolean isAtMiddlePosition() {
		boolean result = false;
		switch(currentCannonPosition) {
		case Top:
			
			if (cannonLiftMotor.getEncPosition() <= middleEncoderPosition) {
				currentCannonPosition = CannonPosition.Middle;
			}
		case Middle:
			result = true;
		case Bottom:
			if(cannonLiftMotor.getEncPosition() >= middleEncoderPosition) {
				currentCannonPosition = CannonPosition.Middle;				
			}
		}
		return result;
	}
	
	public boolean isAtTopPosition() {
		
		if(cannonLiftMotor.getEncPosition() >= topEncoderPosition) {
			currentCannonPosition = CannonPosition.Top;
			return true;
		}
		return false;
	}
	
	public void resetEncoder() {
		cannonLiftMotor.setEncPosition(0);
	}
	
	public CannonPosition getCurrentPosition() {
		return currentCannonPosition;
	}

}