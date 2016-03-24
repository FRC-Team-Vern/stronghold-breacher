package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup2 extends CommandGroup{
	public OuterWorksGroup2(){

	//addSequential (new SquareUp());
	addSequential (new MoveArmsDown());
	addSequential (new DriveStraight(0, 0.5));
	//addSequential (new TriangleUp());
	
	}
}
