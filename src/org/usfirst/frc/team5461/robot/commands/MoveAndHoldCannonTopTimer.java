package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndHoldCannonTopTimer extends CommandGroup {
    
    public  MoveAndHoldCannonTopTimer() {
        addSequential(new MoveCannonToTopPosition());
        addSequential(new HoldCannonTopTimer());
    }
}
