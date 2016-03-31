package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterServos extends Subsystem {
    
	Servo cannonTopPusher;
	Servo cannonBottomPusher;

	private static final double bottomPusherIn = 1;
	private static final double bottomPusherOut = 0.5;
	private static final double topPusherIn = 0;
	private static final double topPusherOut = 0.5;

	public ShooterServos() {
		cannonTopPusher = new Servo(0);
		cannonBottomPusher = new Servo(1);

	}

	public void moveServosIn() {
		cannonBottomPusher.set(bottomPusherIn);
		cannonTopPusher.set(topPusherIn);
	}
	
	public void moveServosOut() {
		cannonBottomPusher.set(bottomPusherOut);
		cannonTopPusher.set(topPusherOut);
	}
	
	    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

