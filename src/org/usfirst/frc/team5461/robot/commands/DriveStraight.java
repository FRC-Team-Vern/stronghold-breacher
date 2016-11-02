/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5461.robot.Robot;

/**
 * Drive the given distance straight (negative values go backwards).
 * Uses a local PID controller to run a simple PID loop that is only
 * enabled while this command is running. The input is the averaged
 * values of the left and right encoders. 
 */
public class DriveStraight extends Command {
    private PIDController pid;
	private static final double kP_real = .001;
	private static final double kI_real = 0.00;
	private static final double kD_real = 0;
    
    public DriveStraight(double distance, double power) {
        requires(Robot.driveTrainLeft);
        requires(Robot.driveTrainRight);
        pid = new PIDController(kP_real, kI_real, 0,
                new PIDSource() { 
            PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
            public double pidGet() {
                    return ((Robot.driveTrainLeft.getDistance() + Robot.driveTrainRight.getDistance())*0.5);
                   
                }

				@Override
				public void setPIDSourceType(PIDSourceType pidSource) {
					m_sourceType = pidSource;
					
				}

				@Override
				public PIDSourceType getPIDSourceType() {
					return m_sourceType;
				}},
                new PIDOutput() { public void pidWrite(double d) {
                	double newValue = d * power;
                    Robot.driveTrainRight.drive(newValue);
                    Robot.driveTrainLeft.drive(newValue);
                }});
        pid.setAbsoluteTolerance(100.0);
        pid.setSetpoint(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Get everything in a safe starting state.
        Robot.driveTrainLeft.reset();
        Robot.driveTrainRight.reset();
    	pid.reset();
        pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Drive Straight Distance", ((Robot.driveTrainLeft.getDistance() + Robot.driveTrainRight.getDistance())*0.5));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Stop PID and the wheels
    	pid.disable();
        Robot.driveTrainLeft.drive(0);
        Robot.driveTrainRight.drive(0);
        Robot.flatIron.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
