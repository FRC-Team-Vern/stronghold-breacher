package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
/**
 *
 */
public class DriveTrainLeft extends PIDSubsystem {
	private CANTalon front_left_motor, back_left_motor;
    // Initialize your subsystem here
    public DriveTrainLeft() {
    	super(DriveTrainContract.kP_real,DriveTrainContract.kI_real,DriveTrainContract.kD_real);
		setPercentTolerance(DriveTrainContract.k_tolPercent);
		setOutputRange(DriveTrainContract.k_ouptutMin, DriveTrainContract.k_outputMax);
		
		//Front motor
		front_left_motor = new CANTalon(8);
		front_left_motor.setInverted(true);
		front_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		front_left_motor.configEncoderCodesPerRev(128);
		
		//Back motor
		back_left_motor = new CANTalon(11);
		back_left_motor.setInverted(true);
		back_left_motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		back_left_motor.configEncoderCodesPerRev(128);
    	
		PIDController controller = getPIDController();
		
		//TODO: Find a way to set source type
		//	setPIDSourceType(PIDSourceType.kRate);
		setInputRange(DriveTrainContract.k_inputMin,DriveTrainContract.k_inputMax);


    }
    public void drive(double left) {
		setSetpoint(left);
		
		//TODO: Implement flatiron
		//FlatIron.Pair<Double>adjustFactors=Robot.flatIron.getAdjustmentFactors();
		//double left_results=m_results.get(0)*adjustFactors.m_leftval;
		//double right_results=m_results.get(1)*adjustFactors.m_rightval;

		
		//drive.tankDrive(m_results.get(0), m_results.get(1));
		
		
		//TODO: Remove if PID working
		//front_left_motor.set(left_results);
		//back_left_motor.set(left_results);
		
		
		//SmartDashboard.putNumber("PID Result 0", m_results.get(0));
		//SmartDashboard.putNumber("PID Result 1", m_results.get(1));
	}
    
    public void drive(Joystick joy) {
		Double leftVal=-joy.getRawAxis(Joystick.AxisType.kY.value);
		leftVal=applyDeadband(leftVal);
		
		
		drive(leftVal);
	}
    
    private Double applyDeadband(Double val) {
		
    	if(Math.abs(val)<0.1){
    		return new Double (0.0);
    	}
    	return val;
	}

    
    
    public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());

    }
    
    protected double returnPIDInput() {
     
    	return getAverageSpeed(front_left_motor, back_left_motor);
    }
    
    protected void usePIDOutput(double output) {
       
    }
    protected double getAverageSpeed(CANTalon first, CANTalon second) {
		return (double)(((double)(first.getEncVelocity() + second.getEncVelocity()) * 0.5) / DriveTrainContract.k_inputMax);
	}
}
