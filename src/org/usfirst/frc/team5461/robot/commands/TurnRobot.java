package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRobot extends Command {
	private short turnRobotDegrees;
	public TurnRobot(short degrees) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		turnRobotDegrees = degrees;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (turnRobotDegrees < 0){
			Robot.drivetrain.drive(-0.5,0.5);
		}else if(turnRobotDegrees > 0){
			Robot.drivetrain.drive(0.5,-0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean is_finished = false;
		if (turnRobotDegrees < 0){
			if (Robot.drivetrain.getImuZValue() <= turnRobotDegrees){
				is_finished = true;
			}else{
				is_finished = false;
			}
		}else if(turnRobotDegrees > 0){
			if (Robot.drivetrain.getImuZValue() >= turnRobotDegrees){
				is_finished = true;
			}else{
				is_finished = false;
			}
		}
		return is_finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stopRobot();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.stopRobot();
	}
}
