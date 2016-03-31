
package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The main autonomous command to pickup and deliver the
 * soda to the box.
 */
public class Autonomous extends CommandGroup {
    public Autonomous(CommandGroup autonomousPhase1, CommandGroup autonomousPhase2) {
    	addSequential(autonomousPhase1);
        addSequential(autonomousPhase2);
    }
}
