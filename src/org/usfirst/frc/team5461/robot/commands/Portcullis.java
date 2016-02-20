package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Portcullis extends CommandGroup  {
	public Portcullis(){
		
		//addSequential(new SquareUp());
		addParallel (new MoveArmsDown());
		addSequential (new DriveStraight(0));
		addSequential (new DriveStraight(0));
		
	}
	
}
