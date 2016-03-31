package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFireCannon extends CommandGroup {
    
    public  AutoFireCannon() {
        addSequential(new TimeDelay(0.5));
        addSequential(new FireCannon());
        addSequential(new StopCannonHold());
        addSequential(new StopShooterMotors());
    }
}
