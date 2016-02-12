package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChevalDeFries extends CommandGroup {
		public ChevalDeFries(){
			
			addSequential(new MoveArmsMiddle());
			//addSequential();
			addSequential(new MoveArmsDown());
			//addSequential();
			addSequential(new MoveArmsUp());
			//addParallel();
		}
	
	}
