package org.usfirst.frc.team5461.robot;

import org.usfirst.frc.team5461.robot.commands.ChevalDeFries;
import org.usfirst.frc.team5461.robot.commands.Chomp;
import org.usfirst.frc.team5461.robot.commands.DriveStraight;
import org.usfirst.frc.team5461.robot.commands.EnableflatIron;
import org.usfirst.frc.team5461.robot.commands.FireCannon;
import org.usfirst.frc.team5461.robot.commands.MoveAndHoldCannonMiddlePosition;
import org.usfirst.frc.team5461.robot.commands.MoveAndHoldCannonTopPosition;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDown;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDownManual;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUp;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUpManual;
import org.usfirst.frc.team5461.robot.commands.MoveCannonToBottomPosition;
import org.usfirst.frc.team5461.robot.commands.MoveCannonUp;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroup1;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroup2;
import org.usfirst.frc.team5461.robot.commands.ResetCannonPosition;
import org.usfirst.frc.team5461.robot.commands.RunShooterMotor;
import org.usfirst.frc.team5461.robot.commands.StopCannonHold;
import org.usfirst.frc.team5461.robot.commands.StopShooterMotors;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;
import org.usfirst.frc.team5461.robot.commands.TurnRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick logitechJoystick = new Joystick(1);
	private Joystick shooterJoystick = new Joystick(2);
	
	private JoystickButton logitechRightTrigger;
	private JoystickButton logitechLeftTrigger;
	private JoystickButton logitechRightButton;
	private JoystickButton logitechLeftButton;
	private DPadButton dpadUp;
	private DPadButton dpadDown;
	private DPadButton dpadLeft;
	private DPadButton dpadRight;
	private JoystickButton shooterRightTrigger;
	private JoystickButton shooterRightButton;
	private JoystickButton shooterLeftButton;
	private JoystickButton shooterLeftTrigger;
	
	private DPadButton shooterDPadUp;
	private DPadButton shooterDPadDown;
	private DPadButton shooterDPadLeft;
	private DPadButton shooterDPadRight;
	
	public OI() {

		// Create some buttons
		JoystickButton x = new JoystickButton(logitechJoystick, 1);
		JoystickButton a = new JoystickButton(logitechJoystick, 2);
		JoystickButton b = new JoystickButton(logitechJoystick, 3);
		JoystickButton y = new JoystickButton(logitechJoystick, 4);
		JoystickButton back = new JoystickButton(logitechJoystick, 9);
//		JoystickButton start = new JoystickButton(logitechJoystick, 10);
		logitechRightTrigger = new JoystickButton(logitechJoystick, 8);
		logitechLeftTrigger = new JoystickButton(logitechJoystick, 7);
		logitechRightButton = new JoystickButton(logitechJoystick, 6);
		logitechLeftButton = new JoystickButton(logitechJoystick, 5);
		shooterDPadUp = new DPadButton(new Point(0, 1), shooterJoystick);
		shooterDPadRight = new DPadButton(new Point(1, 0), shooterJoystick);
		shooterDPadDown = new DPadButton(new Point(0, -1), shooterJoystick);
		shooterDPadLeft = new DPadButton(new Point(-1, 0), shooterJoystick);
		JoystickButton shooterA= new JoystickButton(shooterJoystick, 2);
		JoystickButton shooterY =new JoystickButton(shooterJoystick, 4);
		JoystickButton shooterX = new JoystickButton(shooterJoystick, 1);
		dpadUp = new DPadButton(new Point(0, 1), logitechJoystick);
		dpadRight = new DPadButton(new Point(1, 0), logitechJoystick);
		dpadDown = new DPadButton(new Point(0, -1), logitechJoystick);
		dpadLeft = new DPadButton(new Point(-1, 0), logitechJoystick);
		shooterRightTrigger = new JoystickButton(shooterJoystick, 8);
		shooterLeftTrigger = new JoystickButton(shooterJoystick, 7);
		shooterLeftButton = new JoystickButton(shooterJoystick, 5);
		shooterRightButton = new JoystickButton(shooterJoystick, 6);
		JoystickButton shooterBack = new JoystickButton(shooterJoystick, 9);

		// Connect the buttons to commands
		logitechRightTrigger.whenPressed(new MoveArmsDown());
		logitechRightButton.whenPressed(new MoveArmsUp());
		logitechLeftTrigger.whileHeld(new EnableflatIron());

		dpadRight.whenPressed(new ChevalDeFries());
		dpadDown.whenPressed(new OuterWorksGroup1());
		dpadLeft.whenPressed(new OuterWorksGroup2());
		dpadUp.whenPressed(new DriveStraight(2000, 0.75));
		
		a.whenPressed(new TankDriveWithJoystick());
		x.whenPressed(new TurnRobot((short)-90));
		back.whenPressed(new MoveArmsDownManual());
		
		shooterRightButton.whileHeld(new ResetCannonPosition());
		shooterLeftButton.whileHeld(new MoveArmsUpManual());
		shooterLeftTrigger.whileHeld(new MoveArmsDownManual());
		shooterY.whileHeld(new RunShooterMotor());
		shooterX.whileHeld(new MoveCannonUp());
		shooterA.whileHeld(new Chomp());
		shooterRightTrigger.whenPressed(new FireCannon());
		shooterDPadUp.whenPressed(new MoveAndHoldCannonMiddlePosition());
		shooterDPadDown.whenPressed(new MoveCannonToBottomPosition());
		shooterDPadRight.whenPressed(new MoveAndHoldCannonMiddlePosition());
		shooterDPadLeft.whenPressed(new MoveAndHoldCannonMiddlePosition());
		shooterBack.whenPressed(new StopCannonHold());
	}

	public Joystick getJoystick() {
		return logitechJoystick;
	}

	public Point getDPadValue(Joystick joystick) {
		int direction = joystick.getPOV(0);
		Point point = new Point();
		point.y = (int)Math.cos(Math.toRadians(direction));
		point.x = (int)Math.sin(Math.toRadians(direction));
		return point;
	}

	public boolean getRightTriggerThreshold() {
		return logitechRightTrigger.get();
	}

	public boolean getLeftTriggerThreshold() {
		return logitechLeftTrigger.get();
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

