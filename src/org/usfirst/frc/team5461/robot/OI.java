package org.usfirst.frc.team5461.robot;

import org.usfirst.frc.team5461.robot.commands.ChevalDeFries;
import org.usfirst.frc.team5461.robot.commands.Chomp;
import org.usfirst.frc.team5461.robot.commands.DisableflatIron;
import org.usfirst.frc.team5461.robot.commands.DriveStraight;
import org.usfirst.frc.team5461.robot.commands.EnableflatIron;
import org.usfirst.frc.team5461.robot.commands.FireCannon;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDown;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUp;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroupBAndD;
import org.usfirst.frc.team5461.robot.commands.Portcullis;
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

		private JoystickButton logitechRightTrigger;
		private JoystickButton logitechLeftTrigger;
		private JoystickButton logitechRightButton;
		private JoystickButton logitechLeftButton;
		private DPadButton dpadUp;
		private DPadButton dpadDown;
		private DPadButton dpadLeft;
		private DPadButton dpadRight;

	    public OI() {
	    	// Put Some buttons on the SmartDashboard
	     
	        // Create some buttons
	        JoystickButton x = new JoystickButton(logitechJoystick, 1);
	        JoystickButton a= new JoystickButton(logitechJoystick, 2);
	        JoystickButton b= new JoystickButton(logitechJoystick, 3);
	        JoystickButton y = new JoystickButton(logitechJoystick, 4);
	        JoystickButton back= new JoystickButton(logitechJoystick,9);
	        JoystickButton start = new JoystickButton(logitechJoystick,10);
	        logitechRightTrigger = new JoystickButton(logitechJoystick, 8);
	        logitechLeftTrigger = new JoystickButton(logitechJoystick, 7);
	        logitechRightButton = new JoystickButton(logitechJoystick, 6);
	        logitechLeftButton = new JoystickButton(logitechJoystick, 5);
	        dpadUp = new DPadButton(new Point(0, 1));
	        dpadRight = new DPadButton(new Point(1,0));
	        dpadDown = new DPadButton(new Point(0, -1));
	        dpadLeft = new DPadButton(new Point(-1, 0));
	        logitechRightTrigger.whileHeld(new MoveArmsUp());
	        logitechLeftTrigger.whileHeld(new EnableflatIron());
	        logitechLeftTrigger.whenReleased(new DisableflatIron());
	        logitechLeftButton.whenPressed(new FireCannon());
	        
	        // Connect the buttons to commands
			dpadRight.whenPressed(new ChevalDeFries());
			dpadDown.whenPressed(new Portcullis());
			dpadLeft.whenPressed(new OuterWorksGroupBAndD());
			dpadUp.whenPressed(new DriveStraight(2000, 0.75));
			b.whenPressed(new Chomp());
			a.whenPressed(new TankDriveWithJoystick());
			x.whenPressed(new TurnRobot((short)90));
			//start.whenPressed(new EnableflatIron());
			//back.whenPressed(new DisableflatIron());
	    }
	    public Joystick getJoystick() {
	        return logitechJoystick;
	    }
	    
	    public Point getDPadValue() {
	    	int direction = logitechJoystick.getPOV(0);
	    	Point point = new Point();
	    	point.y = (int)Math.cos(Math.toRadians(direction));
	    	point.x = (int)Math.sin(Math.toRadians(direction));
	    	return point;
	    }
	    public boolean getRightTriggerThreshold() {
	    	//return xboxRightTrigger.get();
	    	return logitechRightTrigger.get();
	    }
	    
	    public boolean getLeftTriggerThreshold() {
	    	//return xboxLeftTrigger.get();
	    	return logitechLeftTrigger.get();
	    }
	    
	    private class DPadButton extends Button {
	    	private Point _point;
	    	public DPadButton(Point point) {
	    		_point = point;
	    	}
	    	
	    	@Override
	    	public boolean get() {
	    		Point dpadPoint = getDPadValue();
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

