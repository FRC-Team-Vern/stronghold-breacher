package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDriveForDistance extends Command {
	
	private static final double mDistance=10000;
	
	public TankDriveForDistance (){
		requires(Robot.driveTrainLeft);
		requires(Robot.driveTrainRight);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrainRight.reset();
		Robot.driveTrainLeft.reset();
	}

	@Override
	protected void execute() {
		Robot.driveTrainLeft.drive(0.5);
		Robot.driveTrainRight.drive(0.5);
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveTrainLeft.getDistance()>= mDistance && 
				Robot.driveTrainRight.getDistance()>=mDistance;
	}

	@Override
	protected void end() {
		/* no op */		
	}

	@Override
	protected void interrupted() {
		/* no op */
	}
}
