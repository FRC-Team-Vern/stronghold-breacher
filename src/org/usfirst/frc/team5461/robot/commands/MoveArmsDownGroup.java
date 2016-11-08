package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmsDownGroup extends CommandGroup {
    
    public  MoveArmsDownGroup() {
        addSequential(new MoveArmsDown());
    }
}
