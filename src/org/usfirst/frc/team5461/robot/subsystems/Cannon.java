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

public class Cannon extends Subsystem {

	CANTalon cannonLiftMotor;
	public static final int maxEncoderPosition = 850;
	public static final int topEncoderPosition = 700;
	public static final int middleEncoderPosition = 500;
	public static final int bottomEncoderPosition = 0;
	private static final int bufferEncoderPosition = 80;
	public static final int mobiusEncoderPosition = 2000;
	// top and middle encoder positions should be more than 2x buffer distance apart
	

	public static final double cannonUpSlowMotorPower = 0.45;
	public static final double cannonUpQuickMotorPower = 0.75;
	public static final int holdCannonTolerance = 10;
	
	public static final double kP_real_hold = 0.005;
	public static final double kI_real_hold = 0.00;
	public static final double kD_real_hold = 0.00;

	private CannonPosition currentCannonPosition;
	
	public enum CannonPosition {
		Bottom,
		Middle,
		Top
	}
	
	public Cannon(){		
		cannonLiftMotor = new CANTalon(17);
		cannonLiftMotor.setExpiration(0.1);
		cannonLiftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		cannonLiftMotor.configEncoderCodesPerRev(497);
		currentCannonPosition = CannonPosition.Bottom;
		resetEncoder();
	}
	
	@Override
	protected void initDefaultCommand() {
		/* no op*/
	}
	
	public void moveCannonByPower(double power) {
		if (power>0){
			cannonLiftMotor.set(power);
		} else {
			stopCannon();
			System.out.println("Attempting to set cannon motor power negative.");
			
		}
	}
	public void moveCannonUpSlow() {
		cannonLiftMotor.set(-1.0*cannonUpSlowMotorPower);
	}
	
	public void moveCannonUpQuick() {
		cannonLiftMotor.set(-1.0*cannonUpQuickMotorPower);
	}
	
	
	public void stopCannon() {
		cannonLiftMotor.set(0);
	}
	
	public int getEncoderValue() {
		return -1*cannonLiftMotor.getEncPosition();
	}
	
	public void resetEncoder() {
		cannonLiftMotor.setEncPosition(0);
		System.out.println("Resetting Cannon Encoder.");
	}
	
	public CannonPosition getCurrentPosition() {	
		int currentEncoderValue = getEncoderValue();
		
		if(currentEncoderValue >= (mobiusEncoderPosition - bufferEncoderPosition)){
			currentCannonPosition = CannonPosition.Bottom;
		} else if (currentEncoderValue >= (topEncoderPosition - bufferEncoderPosition)) {
			currentCannonPosition = CannonPosition.Top;
		} else if ((middleEncoderPosition + bufferEncoderPosition) > currentEncoderValue && 
				currentEncoderValue >= (middleEncoderPosition - bufferEncoderPosition) ) {
			currentCannonPosition = CannonPosition.Middle;
		} else if ((bottomEncoderPosition + bufferEncoderPosition) > currentEncoderValue) {
			currentCannonPosition = CannonPosition.Bottom;
		}
		
		return currentCannonPosition;
	}
	
	public void log() {
		SmartDashboard.putNumber("Cannon Encoder Value", getEncoderValue());
		SmartDashboard.putString("Cannon Position", currentCannonPosition.toString());
	}
}