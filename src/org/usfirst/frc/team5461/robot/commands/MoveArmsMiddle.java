package org.usfirst.frc.team5461.robot.commands;
import org.usfirst.frc.team5461.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class MoveArmsMiddle extends Command {
	public MoveArmsMiddle(){
		requires(Robot.arms);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Robot.arms.moveArmsUp();
		
		
	}

	@Override
	protected boolean isFinished() {
	return !Robot.arms.getMiddleArmSwitchValue();
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