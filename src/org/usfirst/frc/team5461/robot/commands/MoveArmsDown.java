package org.usfirst.frc.team5461.robot.commands;
import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MoveArmsDown extends Command {
	public MoveArmsDown (){
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
		return Robot.arms.isAtBottomPosition();
	}

	@Override
	protected void end() {
		Robot.arms.armsStop();
		
	}

	@Override
	protected void interrupted() {
		Robot.arms.armsStop();
		
	}
	

}
