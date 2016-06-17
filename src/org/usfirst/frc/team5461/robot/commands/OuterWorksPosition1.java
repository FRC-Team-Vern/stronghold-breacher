package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.CommandLock;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuterWorksPosition1 extends CommandGroup {

	public OuterWorksPosition1() {
		CommandLock commandLock = new CommandLock(true);
		addSequential (new DriveStraight(400, 0.75));
		addSequential (new TurnRobot((short)-15));
		addSequential (new DriveStraight(400, 0.75));
		addSequential (new MoveCannonToMiddlePosition());
		addParallel (new HoldCannonMiddlePosition(commandLock));
		addParallel (new RunShooterMotor());
		addSequential(new ReleaseLockAfterCommand(new AutoFireCannon(), commandLock));
	}
}
