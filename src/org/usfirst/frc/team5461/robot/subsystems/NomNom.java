package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NomNom extends Subsystem {
    
CANTalon motor = new CANTalon(5);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
  
    public void moveBackward() {
    	motor.set(-0.7);
    }
    
    public void stop() {
    	motor.set(0.0);
    }
}

