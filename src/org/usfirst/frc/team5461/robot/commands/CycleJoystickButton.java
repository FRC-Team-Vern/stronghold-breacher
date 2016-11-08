package org.usfirst.frc.team5461.robot.commands;

import java.util.Enumeration;
import java.util.Vector;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CycleJoystickButton extends JoystickButton {

	//	private Vector<CommandGroup> m_commandGroups;

	public CycleJoystickButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
	}

	public void cycleWhenPressed(Vector<CommandGroup> commandGroups) {
		//		m_commandGroups = commandGroups;
		new CycleButtonScheduler() {

			//			boolean pressedLast = grab();
			boolean pressedLast = get();
			private Vector<CommandGroup> m_commandGroups = commandGroups;
			private Enumeration<CommandGroup> m_commandEnumeration = m_commandGroups.elements();
			private CommandGroup currentCommand = m_commandEnumeration.nextElement();

			public void execute() {
				if (get()) {
					if (!pressedLast) {
						pressedLast = true;
						if (currentCommand.isRunning()) {
							System.out.println("Current Command being canceled.");
							currentCommand.cancel();
						}
						if (m_commandEnumeration.hasMoreElements()) {
							currentCommand = m_commandEnumeration.nextElement();
						} else {
							currentCommand = null;
							m_commandEnumeration = m_commandGroups.elements();
							currentCommand = m_commandEnumeration.nextElement();
						}
						System.out.println("New Command being started.");
						currentCommand.start();
					}
				} else {
					pressedLast = false;
				}
			}
		}.start();
	}

	public abstract class CycleButtonScheduler extends ButtonScheduler {
		public abstract void execute();

		protected void start() {
			super.start();
		}
	}

}
