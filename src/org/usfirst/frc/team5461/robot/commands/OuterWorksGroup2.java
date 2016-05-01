package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.subsystems.FlatIron;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksGroup2 extends CommandGroup{
	public OuterWorksGroup2(){


		addSequential (new MoveArmsDown());
		addSequential(new DriveStraight(800, .75));


	}
}
