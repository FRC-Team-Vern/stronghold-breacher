
package org.usfirst.frc.team5461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team5461.sensors.VL6180xIdentification;
import org.usfirst.frc.team5461.sensors.VL6180x;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5461.robot.subsystems.Arms;
import org.usfirst.frc.team5461.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5461.sensors.VL6180xALSGain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain drivetrain;
	public static OI oi;
	public static Arms arms;
	
	final static int vl6180xAddress=0x29;
	VL6180xIdentification identification;
	VL6180x proximitySensor;


    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	drivetrain = new DriveTrain();
		oi = new OI();

		identification = new VL6180xIdentification();
		proximitySensor = new VL6180x(vl6180xAddress);
		proximitySensor.getIdentification(identification);
		
		if(proximitySensor.VL6180xInit() != 0)
		{
		System.out.println	("Failure to initialize proximity sensor.");
		}
   
		proximitySensor.defaultSettings();
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		SmartDashboard.putData(drivetrain);
    }
    
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    @Override
	public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        proximitySensor.defaultSettings();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
	public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
	public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
        SmartDashboard.putNumber("proximity distance", proximitySensor.getDistance());
        SmartDashboard.putNumber("ambient light", proximitySensor.getAmbientLight(VL6180xALSGain.GAIN_1));
     
        //SmartDashboard.putNumber("IMU Zangle", imu.getAngleZ());
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
        LiveWindow.run();
      //  SmartDashboard.putData("IMU", imu);
        //SmartDashboard.putNumber("IMU", imu.getAngleZ());
    }
    
    private void log() {
        drivetrain.log();
    }

}
