package org.usfirst.frc.team5461.robot.commands;
import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class BumpArmsUp extends Command {
	
	public BumpArmsUp(){
		requires(Robot.arms);
	}

	@Override
	protected void initialize() {
		/* no op */
	}

	@Override
	protected void execute() {
		Robot.arms.moveArmsUp();
	}

	@Override
	protected boolean isFinished() {
//		return Robot.arms.getTopArmSwitchValue();
//		return Robot.arms.isAtTopPosition();
		return false;
	}

	@Override
	protected void end() {
		Robot.arms.armsStop();
		Robot.arms.resetEncoder();
	}

	@Override
	protected void interrupted() {
		Robot.arms.armsStop();
	}
}
