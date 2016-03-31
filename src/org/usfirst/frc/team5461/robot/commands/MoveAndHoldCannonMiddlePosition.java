package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveAndHoldCannonMiddlePosition extends CommandGroup {
    
    public  MoveAndHoldCannonMiddlePosition() {
    	addSequential(new MoveCannonToMiddlePosition());
    	addSequential(new HoldCannonMiddlePosition());
    }
}
