package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.Robot;
import org.usfirst.frc.team5461.robot.commands.TankDriveWithJoystick;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DriveTrainLeft extends PIDRateSubsystem {
	private CANTalon front_left_motor, back_left_motor;
    // Initialize your subsystem here
    public DriveTrainLeft() {
    	super(DriveTrainContract.kP_real, DriveTrainContract.kI_real, DriveTrainContract.kD_real, .05, DriveTrainContract.kF_real);
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
    
		setInputRange(DriveTrainContract.k_inputMin,DriveTrainContract.k_inputMax);
		//enable();

    }
    public double getDistance() {
		return -1*back_left_motor.getEncPosition();
	}
    public void log() {
		
		SmartDashboard.putNumber("Left Back Temp", back_left_motor.getTemperature());
		SmartDashboard.putNumber("Left Front Temp", front_left_motor.getTemperature());
		
		//SmartDashboard.putData("Drive Train PID", getPIDController());
	}
    public void reset() {
		back_left_motor.setEncPosition(0);
		front_left_motor.setEncPosition(0);
	}
    public void drive(double left) {
    	
		//TODO: Implement flatiron
		FlatIron.Pair<Double>adjustFactors=Robot.flatIron.getAdjustmentFactors();
		double left_results=left*adjustFactors.m_leftval;
		//setSetpoint(left);
    	usePIDOutput(left_results);
		//System.out.println("Left Set: " + Double.toString(getPIDController().getSetpoint()));
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
    	double averageSpeed = getAverageSpeed(front_left_motor, back_left_motor);
    	return averageSpeed;
    }
    
    protected void usePIDOutput(double output) {
    	front_left_motor.set(output);
    	back_left_motor.set(output);
    	//System.out.println("Output Left: " + Double.toString(output));
    }
    
    protected double getAverageSpeed(CANTalon first, CANTalon second) {
		double avgSpeed = (double)(((double)(first.getEncVelocity() + second.getEncVelocity()) * 0.5) / DriveTrainContract.k_inputMax);
		//System.out.println("Avg Speed Left: " + avgSpeed);
		return avgSpeed;
	}
}
