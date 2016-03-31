package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.subsystems.FlatIron;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup2 extends CommandGroup{
	public OuterWorksGroup2(){

	//addSequential (new SquareUp());
	addSequential (new MoveArmsDown());
	addSequential (new DriveStraight(1200, 0.75));
	addParallel(new EnableflatIron());
	//addSequential (new TriangleUp());
	
	}
}
