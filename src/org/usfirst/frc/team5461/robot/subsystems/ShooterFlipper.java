package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
	
	
/**
 *
 */
public class ShooterFlipper extends Subsystem {
	private static final double flipperMotorPower = 0.75;
	
	DigitalInput limitSwitch;
	private CANTalon flipperMotor;
	Counter counter;
		public ShooterFlipper(){
			limitSwitch= new DigitalInput(9);
			counter = new Counter(limitSwitch);
			flipperMotor= new CANTalon(5);
		}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isSwitchSet(){
    	return counter.get() > 0;
    
    }
    
    public void runFlipperMotor(){
    	flipperMotor.set(-1.0*flipperMotorPower);
    }
    
    public void stopFlipperMotor(){
    	flipperMotor.set(0);
    }
    
    public void initializeCounter() {
        counter.reset();
    }

	public void log() {
		
	}

}

