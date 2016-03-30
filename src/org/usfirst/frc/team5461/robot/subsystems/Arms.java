package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.subsystems.CannonSubsystem.CannonPosition;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;




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
		armMotor = new CANTalon(12);
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
}

