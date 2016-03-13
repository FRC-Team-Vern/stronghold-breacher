package org.usfirst.frc.team5461.robot;

import org.usfirst.frc.team5461.robot.commands.ChevalDeFries;
import org.usfirst.frc.team5461.robot.commands.DisableflatIron;
import org.usfirst.frc.team5461.robot.commands.DriveStraight;
import org.usfirst.frc.team5461.robot.commands.EnableflatIron;
import org.usfirst.frc.team5461.robot.commands.MoveArmsDown;
import org.usfirst.frc.team5461.robot.commands.MoveArmsUp;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroupBAndD;
import org.usfirst.frc.team5461.robot.commands.Portcullis;
import edu.wpi.first.wpilibj.Joystick;
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
	        
	        logitechRightTrigger.whenPressed(new MoveArmsUp());
	        logitechLeftTrigger.whenPressed(new MoveArmsDown());

	        // Connect the buttons to commands
			x.whenPressed(new ChevalDeFries());
			y.whenPressed(new Portcullis());
			b.whenPressed(new OuterWorksGroupBAndD());
			a.whenPressed(new DriveStraight(400));
			start.whenPressed(new EnableflatIron());
			back.whenPressed(new DisableflatIron());
	    }
	    public Joystick getJoystick() {
	        return logitechJoystick;
	    }
	    
	    public boolean getRightTriggerThreshold() {
	    	//return xboxRightTrigger.get();
	    	return logitechRightTrigger.get();
	    }
	    
	    public boolean getLeftTriggerThreshold() {
	    	//return xboxLeftTrigger.get();
	    	return logitechLeftTrigger.get();
	    }

}

