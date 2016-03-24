package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksPosition3 extends CommandGroup{
	public OuterWorksPosition3(){

	//addSequential (new SquareUp());
	addSequential (new DriveStraight(0, 0.5));
	//addSequential (new TriangleUp());
	
	}
}
