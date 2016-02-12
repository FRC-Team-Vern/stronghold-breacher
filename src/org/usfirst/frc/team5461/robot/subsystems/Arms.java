package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;




public class Arms extends Subsystem {
    
	CANTalon armMotor;

	DigitalInput  armSwitchUp;
	
	DigitalInput armSwitchDown;
	
	DigitalInput armSwitchMiddle;
	
	public Arms(){
		armMotor = new CANTalon(0);
		armSwitchUp = new DigitalInput(1);
	    armSwitchDown = new DigitalInput(2);
	    armSwitchMiddle = new DigitalInput(3);
	    
	}
	
	
	public boolean getTopArmSwitchValue(){
		return armSwitchUp.get();
	}
	public boolean getBottomArmSwitchValue(){
		return armSwitchDown.get();
	}
	public boolean getMiddleArmSwitchValue(){
		return armSwitchMiddle.get();
	}

	public void  moveArmsDown(){
		armMotor.set(1);
	}
	public void  moveArmsUp(){
		armMotor.set(-1);
	}
	public void  armsStop(){
		armMotor.set(0);
	}
	
	

    public void initDefaultCommand() {
       
    	
    
    }
}

