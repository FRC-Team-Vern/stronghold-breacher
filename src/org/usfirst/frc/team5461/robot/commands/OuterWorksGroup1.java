package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.CommandLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup1 extends CommandGroup{
	public OuterWorksGroup1(){
		CommandLock cannonHoldLock = new CommandLock(true);
		addSequential (new MoveArmsUp());
		addSequential(new EnableflatIron());
		addSequential(new MoveCannonToTopPosition());
		addParallel(new HoldCannonTopPosition(cannonHoldLock));
		addSequential(new ReleaseLockAfterCommand(new DriveStraight(800, .75), cannonHoldLock));
	}
}
