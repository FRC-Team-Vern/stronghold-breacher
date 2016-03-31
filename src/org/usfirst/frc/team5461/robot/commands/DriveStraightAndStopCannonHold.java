package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightAndStopCannonHold extends CommandGroup {
    
    public  DriveStraightAndStopCannonHold() {
    	addSequential (new DriveStraight(1400, .75));
    	addSequential(new StopCannonHold());
    }
}
