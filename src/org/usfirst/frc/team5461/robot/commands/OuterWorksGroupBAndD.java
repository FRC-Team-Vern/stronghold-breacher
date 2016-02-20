package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroupBAndD extends CommandGroup{
	public OuterWorksGroupBAndD(){

	//addSequential (new SquareUp());
	addParallel (new MoveArmsUp());
	addSequential (new DriveStraight(0));
	//addSequential (new TriangleUp());

	
	}
}
