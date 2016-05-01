package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup1 extends CommandGroup{
	public OuterWorksGroup1(){

		addSequential (new MoveArmsUp());
		//addSequential(new EnableflatIron());
		//addSequential(new MoveAndHoldCannonTopTimer());
		//addSequential(new MoveCannonToTopPosition());
		addParallel(new DriveStraight(800, .75));
		//addSequential(new StopCannonHold());
		


	}
}
