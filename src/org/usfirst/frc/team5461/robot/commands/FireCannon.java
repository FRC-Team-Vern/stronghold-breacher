package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireCannon extends Command{
	public static final double holdServosTime = 3.0;
	
	public FireCannon(){
		requires(Robot.shooterServos);
		setTimeout(holdServosTime);
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
		return isTimedOut();
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
