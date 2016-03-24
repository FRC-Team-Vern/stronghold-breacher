package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;




public class Arms extends Subsystem {
    
	private static final double ARM_MOVEMENT_POWER = 0.5;
	CANTalon armMotor;
	DigitalInput  armSwitchUp;
	DigitalInput armSwitchDown;
	DigitalInput armSwitchMiddle;
	
	public Arms() {
		armMotor = new CANTalon(12);
		armSwitchUp = new DigitalInput(6);
	    armSwitchDown = new DigitalInput(7);
	}
	
	public boolean getTopArmSwitchValue() {
		return armSwitchUp.get();
	}
	
	public boolean getBottomArmSwitchValue() {
		return armSwitchDown.get();
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

    public void initDefaultCommand() {
    	/* no op */
    }
}

