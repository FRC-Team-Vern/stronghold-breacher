package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmsUpGroup extends CommandGroup {
    
    public  MoveArmsUpGroup() {
        addSequential(new MoveArmsUp());
    }
}
