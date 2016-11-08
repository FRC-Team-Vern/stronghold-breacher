package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireCannon extends Command{
	
	public FireCannon(){
		requires(Robot.shooterFlipper);
		
	}

	@Override
	protected void initialize() {
		Robot.shooterFlipper.initializeCounter();
	}

	@Override
	protected void execute() {
		Robot.shooterFlipper.runFlipperMotor();
	}

	@Override
	protected boolean isFinished() {
		System.out.println("IsSwitchSet: " + Robot.shooterFlipper.isSwitchSet());
		return Robot.shooterFlipper.isSwitchSet();
		
	}

	@Override
	protected void end() {
		System.out.println("Stopping Flipper Motor");
		Robot.shooterFlipper.stopFlipperMotor();
		Robot.shooterFlipper.initializeCounter();
	}

	@Override
	protected void interrupted() {
		Robot.shooterFlipper.stopFlipperMotor();
		Robot.shooterFlipper.initializeCounter();
	}

}
