package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class HoldCannonMiddlePosition extends PIDCommand {

    public HoldCannonMiddlePosition() {
    	super(Cannon.kP_real_hold, Cannon.kI_real_hold, Cannon.kD_real_hold);
        requires(Robot.cannon);
        setInputRange(0, Cannon.maxEncoderPosition);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("HoldCannonMiddlePosition Initialized");
    	getPIDController().reset();
    	setSetpoint(Cannon.middleEncoderPosition);
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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

	@Override
	protected double returnPIDInput() {
		return Robot.cannon.getEncoderValue();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.cannon.moveCannonByPower(-1.0*output);
	}
}
