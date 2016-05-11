package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class HoldCannonTopTimer extends PIDCommand {
	
    public HoldCannonTopTimer() {
    	super(Cannon.kP_real_hold, Cannon.kI_real_hold, Cannon.kD_real_hold);
        requires(Robot.cannon);
        setInputRange(0, Cannon.maxEncoderPosition);
        setTimeout(13);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("HoldCannonTopPosition Initialized");
    	getPIDController().reset();
    	setSetpoint(Cannon.topEncoderPosition);
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // This command never truly finishes. Use the StopCannonHold command to break out of this.
    protected boolean isFinished() {
        return  isTimedOut();
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
