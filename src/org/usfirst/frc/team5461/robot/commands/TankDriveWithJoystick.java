package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class TankDriveWithJoystick extends Command {
    
    public TankDriveWithJoystick() {
        requires(Robot.driveTrainRight);
        requires(Robot.driveTrainLeft);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("TankDriveWithJoystick initialized:");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrainRight.drive(Robot.oi.getJoystick());
        Robot.driveTrainLeft.drive(Robot.oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrainLeft.drive(0);
        Robot.driveTrainRight.drive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
