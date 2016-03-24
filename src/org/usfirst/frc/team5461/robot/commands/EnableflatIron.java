package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EnableflatIron extends Command {
	 public EnableflatIron() {
		 requires(Robot.flatIron);
	 }

	@Override
	protected void initialize() {
		Robot.flatIron.enable();
	}

	@Override
	protected void execute() {
		/* no op */		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.flatIron.disable();
	}

	@Override
	protected void interrupted() {
		/* no op */		
	}
}

