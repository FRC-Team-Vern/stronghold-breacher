package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksPosition4 extends CommandGroup {
	
	public OuterWorksPosition4() {
		addSequential (new DriveStraight(200, 0.5));
		addSequential (new TurnRobot((short)5));
		addSequential (new DriveStraight(200, 0.5));
	}
}
