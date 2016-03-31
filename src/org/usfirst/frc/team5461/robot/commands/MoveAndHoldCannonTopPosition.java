package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndHoldCannonTopPosition extends CommandGroup {
    
    public  MoveAndHoldCannonTopPosition() {
        addSequential(new MoveCannonToTopPosition());
        addSequential(new HoldCannonTopPosition());
    }
}
