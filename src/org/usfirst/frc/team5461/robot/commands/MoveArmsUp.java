package org.usfirst.frc.team5461.robot.commands;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MoveArmsUp extends Command {
	
	public MoveArmsUp(){
		requires(Robot.arms);
	}

	@Override
	protected void initialize() {
		System.out.println("Initialize MoveArmsUp:");
	}

	@Override
	protected void execute() {
		Robot.arms.moveArmsUp();
	}

	@Override
	protected boolean isFinished() {
		boolean isAtTopPosition = Robot.arms.isAtTopPosition();
		if (isAtTopPosition) {
			System.out.println("MoveArmsUp: is at top position.");
		} else {
			System.out.println("MoveArmsUp: not at top position.");
		}
		return isAtTopPosition;
	}

	@Override
	protected void end() {
		System.out.println("End MoveArmsUp:");
		Robot.arms.armsStop();	
	}

	@Override
	protected void interrupted() {
		Robot.arms.armsStop();
	}
}
