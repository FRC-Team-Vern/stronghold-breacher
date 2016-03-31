package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup1 extends CommandGroup{
	public OuterWorksGroup1(){

	//addSequential (new SquareUp());
	addSequential (new MoveArmsUp());
	addSequential (new DriveStraight(0, 0.5));
	//addSequential (new TriangleUp());
	
	}
}
