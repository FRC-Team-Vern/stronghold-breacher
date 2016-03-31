package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.subsystems.Cannon.CannonPosition;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




public class Arms extends Subsystem {
    
	private static final double ARM_MOVEMENT_POWER = 0.5;
	CANTalon armMotor;
	
	private ArmPosition currentArmPosition;
	
	private static final int bottomEncoderPosition = 0;
	private static final int topEncoderPosition = 200;

	public enum ArmPosition {
		Top,
		Bottom
	}
	
	public Arms() {
		armMotor = new CANTalon(5);
		armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		armMotor.configEncoderCodesPerRev(497);

		currentArmPosition = ArmPosition.Top;
		resetEncoder();
	}

	public void resetEncoder() {
		armMotor.setEncPosition(0);
	}
	
	public void moveArmsDown() {
		armMotor.set(ARM_MOVEMENT_POWER);
	}
	
	public void moveArmsUp() {
		armMotor.set(-ARM_MOVEMENT_POWER);
	}
	
	public void armsStop() {
		armMotor.set(0);
	}	

	public boolean isAtBottomPosition() {
		if(armMotor.getEncPosition() <= bottomEncoderPosition) {
			currentArmPosition = ArmPosition.Bottom;
			return true;
		}
		return false;
	}
	
	public boolean isAtTopPosition() {
		
		if(armMotor.getEncPosition() >= topEncoderPosition) {
			currentArmPosition = ArmPosition.Top;
			return true;
		}
		return false;
	}
	
	public ArmPosition getCurrentArmPosition() {
		return currentArmPosition;
	}
	
    public void initDefaultCommand() {
    	/* no op */
    }
    
    public void log() {
    	SmartDashboard.putNumber("Arm Encoder",armMotor.getEncPosition());
    }
}

