package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCannonToBottomPosition extends Command {

	private static final int bottomEncoderPosition = 0;

    public MoveCannonToBottomPosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cannon.setSetpoint(bottomEncoderPosition);
    	Robot.cannon.getPIDController().reset();
    	Robot.cannon.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cannon.stopCannon();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.cannon.stopCannon();
    }
}
