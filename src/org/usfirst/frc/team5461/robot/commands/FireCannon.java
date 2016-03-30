package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireCannon extends Command{
	public FireCannon(){
		
		requires(Robot.shooterServos);		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Robot.shooterServos.moveServosOut();		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooterServos.moveServosIn();
		
	}

	@Override
	protected void interrupted() {
		Robot.shooterServos.moveServosIn();
	}

}
