package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




public class Arms extends Subsystem {
    
	private static final double ARM_MOVEMENT_POWER = .40;
	private static final int bottomEncoderPosition = -400;
	private static final int topEncoderPosition = 0	;
	
	public void resetEncoder() {
		armMotor.setEncPosition(0);
	}

	CANTalon armMotor;
//	DigitalInput armSwitchDown;
//	DigitalInput armSwitchUp;
	
	private ArmPosition currentArmPosition;

	public enum ArmPosition {
		Top,
		Bottom
	}
	
	public Arms() {
		armMotor = new CANTalon(22);
//		armSwitchUp = new DigitalInput(1);
//	    armSwitchDown = new DigitalInput(2);
	

		currentArmPosition = ArmPosition.Top;
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
	
//			public boolean getTopArmSwitchValue() {
//		 		return !armSwitchUp.get();
//	 	}
//			public boolean getBottomArmSwitchValue() {
//		 		return !armSwitchDown.get();
//		 	}
	
	public ArmPosition getCurrentArmPosition() {
		return currentArmPosition;
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
	
    public void initDefaultCommand() {
    	/* no op */
    }
    

    public void log() {
    	SmartDashboard.putNumber("Arm Encoder",armMotor.getEncPosition());    	
    }
}

