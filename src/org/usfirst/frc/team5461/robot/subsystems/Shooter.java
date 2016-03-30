package org.usfirst.frc.team5461.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	CANTalon shooterMotor;
	
	private static final double shooterMotorPower = 0.75;

	public Shooter() {
		shooterMotor = new CANTalon(17);
		shooterMotor.setExpiration(0.1);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void turnMotorIntoChassis() {
		shooterMotor.set(-shooterMotorPower);
	}
	
	public void turnMotorOutOfChassis() {
		shooterMotor.set(shooterMotorPower);
	}

	public void stopShooterMotor() {
		shooterMotor.set(0);
	}
}

