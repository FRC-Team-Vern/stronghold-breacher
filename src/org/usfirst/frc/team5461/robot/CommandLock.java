package org.usfirst.frc.team5461.robot;

public class CommandLock {

	private boolean m_isLocked;
	
	public CommandLock(boolean isLocked) {
		this.setLocked(isLocked);
	}

	public boolean isLocked() {
		return m_isLocked;
	}

	public void setLocked(boolean m_isLocked) {
		this.m_isLocked = m_isLocked;
	}
	
}
