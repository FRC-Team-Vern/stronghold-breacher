package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCannonToTopPosition extends Command {

    public MoveCannonToTopPosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.moveCannonUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.isAtTopPosition();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cannon.stopLiftMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.cannon.stopLiftMotor();
    }
}
