package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.Cannon.CannonPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveCannonToBottomPosition extends Command {

    public MoveCannonToBottomPosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("MoveCannonToBottomPosition Initialized");
    	Robot.cannon.setCommandPosition(CannonPosition.Bottom);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Robot.cannon.getCurrentPosition()) {
		case Middle:
				Robot.cannon.moveCannonUpQuick();
			break;
		case Top:
				Robot.cannon.moveCannonUpSlow();
			break;
		case Mobius:
				Robot.cannon.moveCannonBackwardSlow();
		case Bottom:
			Robot.cannon.stopCannon();
		default:
			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cannon.getCurrentPosition() == CannonPosition.Bottom;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cannon.stopCannon();
    	Robot.cannon.resetEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.cannon.stopCannon();
    	Robot.cannon.resetEncoder();
    }
}
