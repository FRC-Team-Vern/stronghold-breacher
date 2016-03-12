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
		armMotor = new CANTalon(12);
		armSwitchUp = new DigitalInput(1);
	    armSwitchDown = new DigitalInput(2);
    
	}
	
	
	public boolean getTopArmSwitchValue(){
		return armSwitchUp.get();
	}
	public boolean getBottomArmSwitchValue(){
		return armSwitchDown.get();
	}

	public void  moveArmsDown(){
		armMotor.set(50);
	}
	public void  moveArmsUp(){
		armMotor.set(-50);
	}
	public void  armsStop(){
		armMotor.set(0);
	}
	
	

    public void initDefaultCommand() {
       
    	
    
    }
}

