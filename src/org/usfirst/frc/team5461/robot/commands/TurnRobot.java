package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnRobot extends Command {
	private short turnRobotDegrees;
	private double _startingImuZValue = 0.0;
	public TurnRobot(short degrees) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.flatIron);
		turnRobotDegrees = degrees;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_startingImuZValue  = Robot.flatIron.getImuZValue();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Starting Turn Value", _startingImuZValue);
		SmartDashboard.putNumber("Current Turn Value", Robot.flatIron.getImuZValue());
		if (turnRobotDegrees < 0){
			Robot.driveTrainLeft.drive(-0.5);
			Robot.driveTrainRight.drive(0.5);
		}else if(turnRobotDegrees > 0){
			Robot.driveTrainLeft.drive(0.5);
			Robot.driveTrainRight.drive(-0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean is_finished = false;
		double imuZValue = Robot.flatIron.getImuZValue();
		if (_startingImuZValue == 0 && 0 == imuZValue) {
			return false;
		}
		if (turnRobotDegrees < 0){
			if ((_startingImuZValue - imuZValue) >= turnRobotDegrees){
				is_finished = true;
			}else{
				is_finished = false;
			}
		}else if(turnRobotDegrees > 0){
			if ((imuZValue - _startingImuZValue) <= turnRobotDegrees){
				is_finished = true;
			}else{
				is_finished = false;
			}
		}
		return is_finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrainLeft.drive(0);
		Robot.driveTrainRight.drive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrainLeft.drive(0);
		Robot.driveTrainRight.drive(0);
	}
}
