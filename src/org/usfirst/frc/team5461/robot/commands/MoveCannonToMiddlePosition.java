package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.CannonSubsystem.CannonPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveCannonToMiddlePosition extends Command {

    public MoveCannonToMiddlePosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.cannon.getCurrentPosition() == CannonPosition.Bottom) {
    		Robot.cannon.moveCannonUp();
    	}
    	else if (Robot.cannon.getCurrentPosition() == CannonPosition.Top) {
    		Robot.cannon.moveCannonDown();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.isAtMiddlePosition();
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
