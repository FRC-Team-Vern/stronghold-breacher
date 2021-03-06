package org.usfirst.frc.team5461.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	CANTalon shooterMotor;
	
	private static final double shooterInMotorPower = 0.50;
	private static final double shooterOutMotorPower = 1.0;

	public Shooter() {
		shooterMotor = new CANTalon(18);
		shooterMotor.setExpiration(0.1);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void turnMotorIntoChassis() {
		shooterMotor.set(shooterInMotorPower);
	}
	
	public void turnMotorOutOfChassis() {
		shooterMotor.set(-1.0*shooterOutMotorPower);
	}

	public void stopShooterMotor() {
		shooterMotor.set(0);
	}
}

