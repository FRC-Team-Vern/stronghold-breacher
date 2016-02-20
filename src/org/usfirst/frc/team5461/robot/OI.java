package org.usfirst.frc.team5461.robot;

import org.usfirst.frc.team5461.robot.commands.ChevalDeFries;
import org.usfirst.frc.team5461.robot.commands.DriveStraight;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroupBAndD;
import org.usfirst.frc.team5461.robot.commands.Portcullis;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	    private Joystick joy = new Joystick(0);

	    public OI() {
	    	// Put Some buttons on the SmartDashboard
	     
	        // Create some buttons
	        JoystickButton x = new JoystickButton(joy, 1);
	        JoystickButton a= new JoystickButton(joy, 2);
	        JoystickButton b= new JoystickButton(joy, 3);
	        JoystickButton y = new JoystickButton(joy, 8);
	        

	        // Connect the buttons to commands
	      x.whenPressed(new ChevalDeFries());
	      y.whenPressed(new Portcullis());
	      b.whenPressed(new OuterWorksGroupBAndD());
	      a.whenPressed(new DriveStraight(1));
	    }
	    public Joystick getJoystick() {
	        return joy;
	    }
}

