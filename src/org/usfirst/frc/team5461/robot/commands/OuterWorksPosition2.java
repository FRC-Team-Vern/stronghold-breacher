package org.usfirst.frc.team5461.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksPosition2 extends CommandGroup {

	public OuterWorksPosition2() {

		addSequential (new DriveStraight(400, 0.75));
		addSequential (new TurnRobot((short)-15));
		addSequential (new DriveStraight(400, 0.75));
		addSequential (new MoveCannonToMiddlePosition());
		addSequential (new HoldCannonMiddlePosition());
		addParallel (new RunShooterMotor());
		addParallel(new AutoFireCannon());

	}
}
