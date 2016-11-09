package org.usfirst.frc.team5461.robot;

import java.util.Vector;

import org.usfirst.frc.team5461.robot.commands.Chomp;
import org.usfirst.frc.team5461.robot.commands.CycleJoystickButton;
import org.usfirst.frc.team5461.robot.commands.EnableflatIron;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDown;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDownGroup;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUp;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUpGroup;
import org.usfirst.frc.team5461.robot.commands.MoveCannonToBottomGroup;
import org.usfirst.frc.team5461.robot.commands.MoveCannonToMiddleGroup;
import org.usfirst.frc.team5461.robot.commands.MoveCannonToTopGroup;
import org.usfirst.frc.team5461.robot.commands.MoveCannonUp;
import org.usfirst.frc.team5461.robot.commands.ResetEncoder;
import org.usfirst.frc.team5461.robot.commands.RunShooterMotor;
import org.usfirst.frc.team5461.robot.commands.ShootCannon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick joystick = new Joystick(0);
	
	private DPadButton dpadUp;
	private DPadButton dpadDown;
	private JoystickButton rightTrigger;
	private JoystickButton leftTrigger;
	private JoystickButton a;
	private JoystickButton b;
	private JoystickButton y;
	private JoystickButton x;
	private CycleJoystickButton cycleShooter;
	private CycleJoystickButton cycleArms;
	
	public OI() {
		rightTrigger = new JoystickButton(joystick, 8);
		leftTrigger = new JoystickButton(joystick, 7);
		b = new JoystickButton(joystick, 3);
		a = new JoystickButton(joystick, 2);
		y = new JoystickButton(joystick, 4);
		x = new JoystickButton(joystick, 1);
		dpadUp = new DPadButton(new Point(0, 1), joystick);
		dpadDown = new DPadButton(new Point(0, -1), joystick);
		
		// Cannon movement groups
		cycleShooter = new CycleJoystickButton(joystick, 6);
		Vector<CommandGroup> shooterCycles = new Vector<CommandGroup>();
		shooterCycles.add(new MoveCannonToBottomGroup());
		shooterCycles.add(new MoveCannonToMiddleGroup());
		shooterCycles.add(new MoveCannonToTopGroup());
		cycleShooter.cycleWhenPressed(shooterCycles);
		a.whenPressed(new ResetEncoder());
		b.whileHeld(new MoveCannonUp());

		// Cycle arms groups
		cycleArms = new CycleJoystickButton(joystick, 5);
		Vector<CommandGroup> armCycles = new Vector<CommandGroup>();
		armCycles.add(new MoveArmsUpGroup());
		armCycles.add(new MoveArmsDownGroup());
		cycleArms.cycleWhenPressed(armCycles);
		
		// Manual move arms up and down
		dpadDown.whenPressed(new MoveArmsDown());
		dpadUp.whenPressed(new MoveArmsUp());
		
		// Shooting cannon
		rightTrigger.whenPressed(new ShootCannon());
		y.whileHeld(new RunShooterMotor());
		leftTrigger.whileHeld(new Chomp());
		
		//driving
		x.whileHeld(new EnableflatIron());
	}

	public Joystick getJoystick() {
		return joystick;
	}

	public Point getDPadValue(Joystick joystick) {
		int direction = joystick.getPOV(0);
		Point point = new Point();
		point.y = (int)Math.cos(Math.toRadians(direction));
		point.x = (int)Math.sin(Math.toRadians(direction));
		return point;
	}

	public boolean getRightTriggerThreshold() {
		return rightTrigger.get();
	}

	public boolean getLeftTriggerThreshold() {
		return leftTrigger.get();
	}

	private class DPadButton extends Button {
		private Point _point;
		private Joystick _joystick;
		public DPadButton(Point point, Joystick joystick) {
			_point = point;
			_joystick = joystick;
		}

		@Override
		public boolean get() {
			Point dpadPoint = getDPadValue(_joystick);
			if (_point.x == dpadPoint.x && _point.y == dpadPoint.y) {
				return true;
			}
			return false;
		}		
	}

	private static class Point {
		public int x;
		public int y;

		public Point() {
			/* no op */
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

