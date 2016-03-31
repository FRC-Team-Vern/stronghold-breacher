package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup1 extends CommandGroup{
	public OuterWorksGroup1(){

		addSequential (new MoveArmsUp());
		addParallel(new EnableflatIron());
		addSequential(new MoveAndHoldCannonTopPosition());
		addParallel(new DriveStraightAndStopCannonHold());
		


	}
}
