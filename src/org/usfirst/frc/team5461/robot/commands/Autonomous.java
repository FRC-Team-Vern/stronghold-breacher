
package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The main autonomous command to pickup and deliver the
 * soda to the box.
 */
public class Autonomous extends CommandGroup {
    public Autonomous(Command command, Command command2) {
    	addSequential(command);
        addSequential(command2);
    }
}
