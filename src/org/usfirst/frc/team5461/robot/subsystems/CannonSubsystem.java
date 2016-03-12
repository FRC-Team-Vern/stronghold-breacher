package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CannonSubsystem extends Subsystem {

	CANTalon cannonMotor1;
	CANTalon cannonMotor2;
	public CannonSubsystem(){
		cannonMotor1 = new CANTalon(0);
		cannonMotor1.setExpiration(0.1);
		cannonMotor2 = new CANTalon(1);
		cannonMotor2.setExpiration(0.1);
		
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}