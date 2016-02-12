package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDriveForDistance extends Command {
	private static final double mDistance=10000;
	
	public TankDriveForDistance (){
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.reset();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.drive(0.5,0.5);
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.getDistance()>= mDistance;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
