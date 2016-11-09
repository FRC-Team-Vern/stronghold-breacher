package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EnableflatIron extends Command {
	 public EnableflatIron() {
		 requires(Robot.flatIron);
	 }

	protected void initialize() {
		Robot.flatIron.enable();
	}

	protected void execute() {
		/* no op */		
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.flatIron.disable();
	}

	protected void interrupted() {
		/* no op */	
		Robot.flatIron.disable();
	}
}

