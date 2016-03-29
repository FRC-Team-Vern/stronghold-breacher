package org.usfirst.frc.team5461.robot.commands;
import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MoveArmsUp extends Command {
	
	public MoveArmsUp(){
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
		return false;
		//return Robot.arms.getTopArmSwitchValue();
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
