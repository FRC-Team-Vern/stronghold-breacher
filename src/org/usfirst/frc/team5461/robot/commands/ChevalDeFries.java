package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChevalDeFries extends CommandGroup {
		public ChevalDeFries(){
			//addSequential();
			//addSequential(new MoveArmsUp());
			addSequential(new DriveStraight(0, 0.5));
			//addSequential(new MoveArmsDown());
			addSequential(new DriveStraight(0, 0.5));
			//addSequential(new MoveArmsUp());
			//addParallel();
		}
	
	}