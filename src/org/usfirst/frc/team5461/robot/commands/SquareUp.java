package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.SensorNumber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SquareUp extends Command {
	public static final int TARGET_DISTANCE = 200;
    public SquareUp() {
        requires(Robot.drivetrain);
    	requires(Robot.redRover);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int[] distances = Robot.redRover.getDistanceFromAllSensors();
    	int leftSide = distances[SensorNumber.BottomLeftProximitySensor.getValue()];
    	int rightSide = distances[SensorNumber.BottomRightProximitySensor.getValue()];
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
