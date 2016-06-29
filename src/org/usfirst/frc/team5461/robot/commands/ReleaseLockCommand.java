package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.CommandLock;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseLockCommand extends Command {
	
	private CommandLock m_commandLock;
	
	public ReleaseLockCommand(CommandLock commandLock) {
		this.m_commandLock = commandLock;
	}

	@Override
	protected void initialize() {
		// Just release the lock right out of the gate.
		System.out.println("Releasing lock:");
		m_commandLock.setLocked(false);
	}

	@Override
	protected void execute() {
		// No op
	}

	@Override
	protected boolean isFinished() {
		// Finishes immediately
		return true;
	}

	@Override
	protected void end() {
		// No op
	}

	@Override
	protected void interrupted() {
		// No op
	}

}
