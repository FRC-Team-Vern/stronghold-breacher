package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.Cannon;
import org.usfirst.frc.team5461.robot.subsystems.Cannon.CannonPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveCannonToMiddlePosition extends Command {

    public MoveCannonToMiddlePosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("MoveCannonToTopPosition Initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Robot.cannon.getCurrentPosition()) {
		case Top:
			Robot.cannon.moveCannonUpQuick();
			break;
		case Bottom:
			Robot.cannon.moveCannonUpQuick();
			break;
		case Middle:
		break;
		default:
			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.cannon.getCurrentPosition() == CannonPosition.Middle;
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
