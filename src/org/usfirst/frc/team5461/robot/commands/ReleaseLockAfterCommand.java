package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.CommandLock;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReleaseLockAfterCommand extends CommandGroup {
	
	public ReleaseLockAfterCommand(Command command, CommandLock commandLock) {
		addSequential(command);
		addSequential(new ReleaseLockCommand(commandLock));
	}
}
