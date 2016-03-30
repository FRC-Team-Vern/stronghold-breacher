package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireCannon extends Command{
	public FireCannon(){
		
		requires(Robot.cannon);		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.cannon.moveServosOut();		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.cannon.moveServosIn();
		
	}

	@Override
	protected void interrupted() {
		Robot.cannon.moveServosIn();
	}

}
