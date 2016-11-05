package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.Cannon;
import org.usfirst.frc.team5461.robot.subsystems.Cannon.CannonPosition;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveCannonToTopPosition extends Command {

    public MoveCannonToTopPosition() {
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("MoveCannonToTopPosition Initialized");
    	Robot.cannon.setCommandPosition(CannonPosition.Top);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Robot.cannon.getCurrentPosition()) {
		case Bottom:
		case Middle:
			Robot.cannon.moveCannonUpQuick();
			break;
		case Top:
		default:
			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    // This command should most likely be followed up by a hold at top command.
    protected boolean isFinished() {
        return Robot.cannon.getCurrentPosition() == CannonPosition.Top;
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
