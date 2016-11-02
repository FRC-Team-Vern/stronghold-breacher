
package org.usfirst.frc.team5461.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team5461.sensors.VL6180xIdentification;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5461.robot.commands.Autonomous;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroup1;
import org.usfirst.frc.team5461.robot.commands.OuterWorksGroup2;
import org.usfirst.frc.team5461.robot.commands.OuterWorksPosition1;
import org.usfirst.frc.team5461.robot.commands.OuterWorksPosition2;
import org.usfirst.frc.team5461.robot.commands.OuterWorksPosition3;
import org.usfirst.frc.team5461.robot.commands.OuterWorksPosition4;
import org.usfirst.frc.team5461.robot.commands.OuterWorksPosition5;
import org.usfirst.frc.team5461.robot.commands.StopCannonHold;
import org.usfirst.frc.team5461.robot.subsystems.Arms;
import org.usfirst.frc.team5461.robot.subsystems.Cannon;
import org.usfirst.frc.team5461.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5461.robot.subsystems.DriveTrainLeft;
import org.usfirst.frc.team5461.robot.subsystems.DriveTrainRight;
import org.usfirst.frc.team5461.robot.subsystems.FlatIron;
import org.usfirst.frc.team5461.robot.subsystems.Shooter;
import org.usfirst.frc.team5461.robot.subsystems.ShooterServos;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static FlatIron flatIron;
	public static DriveTrainRight driveTrainRight;
	public static DriveTrainLeft driveTrainLeft;
	public static OI oi;
	public static Arms arms;
	public static Cannon cannon;
	public static Shooter shooter;
	public static ShooterServos shooterServos;
	SendableChooser autoChooserPhase1;
	SendableChooser autoChooserPhase2;

	
	VL6180xIdentification identification;
    Command autonomousCommand;
    CommandGroup autonomousCommandPhase1;
    CommandGroup autonomousCommandPhase2;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
	public void robotInit() {
    	autoChooserPhase1 = new SendableChooser();
    	cannon = new Cannon();
    	shooter = new Shooter();
    	shooterServos = new ShooterServos();
    	flatIron= new FlatIron();
		arms = new Arms();
    	driveTrainLeft = new DriveTrainLeft();
    	driveTrainLeft.reset();
    	driveTrainRight = new DriveTrainRight();
    	driveTrainRight.reset();
    	
		oi = new OI();

    	autoChooserPhase1.addDefault("Nothing", new StopCannonHold());
    	autoChooserPhase1.addObject("Group 1 (Pick up arms)", new OuterWorksGroup1());
    	autoChooserPhase1.addObject("Group 2 (Leave arms down)", new OuterWorksGroup2());
    	
    	autoChooserPhase2 = new SendableChooser();
    	autoChooserPhase2.addDefault("Nothing", new StopCannonHold());
    	autoChooserPhase2.addObject("Position 5", new OuterWorksPosition5());
    	autoChooserPhase2.addObject("Position 4", new OuterWorksPosition4());
    	autoChooserPhase2.addObject("Position 3", new OuterWorksPosition3());
    	autoChooserPhase2.addObject("Position 2", new OuterWorksPosition2());
    	autoChooserPhase2.addObject("Position 1", new OuterWorksPosition1());
    	
    	SmartDashboard.putData("Autonomous Phase 1", autoChooserPhase1);
    	SmartDashboard.putData("Autonomous Phase 2", autoChooserPhase2);
    }
		
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    @Override
	public void autonomousInit() {
    	arms.resetEncoder();
    	cannon.resetEncoder();
        // schedule the autonomous command (example)
    	if (autonomousCommand == null) {
    	autonomousCommand = 
    			new Autonomous((Command)autoChooserPhase1.getSelected(),
    					(Command)autoChooserPhase2.getSelected());
    	}
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
        new StopCannonHold().start();
        arms.resetEncoder();
        cannon.resetEncoder();
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
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
	public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void log() {
        driveTrainRight.log();
        driveTrainLeft.log();
        flatIron.log();
    	arms.log();
    	cannon.log();
    }

}
