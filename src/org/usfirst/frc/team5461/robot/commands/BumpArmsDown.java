package org.usfirst.frc.team5461.robot.commands;
import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class BumpArmsDown extends Command {
	public BumpArmsDown (){
		requires(Robot.arms);
	}

	@Override
	protected void initialize() {
		Robot.arms.resetEncoder();
	}

	@Override
	protected void execute() {
		Robot.arms.moveArmsDown();
	}

	@Override
	protected boolean isFinished() {
//		return Robot.arms.getBottomArmSwitchValue();
//		return Robot.arms.isAtBottomPosition();
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
