package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandC extends Command {

    public CommandC() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.subsystemC);
        setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("CommandC started");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("CommandC ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
