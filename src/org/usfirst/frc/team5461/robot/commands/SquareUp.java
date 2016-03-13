package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.SensorNumber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SquareUp extends Command {
	private double setpoint = 200;
	
    public SquareUp(double setpoint) {
    	this.setpoint = setpoint;
        requires(Robot.drivetrain);
    	//requires(Robot.redRover);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.multiPIDSubsystem.enable();
        //Robot.multiPIDSubsystem.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.multiPIDSubsystem.onTarget();
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
