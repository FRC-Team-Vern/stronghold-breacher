package org.usfirst.frc.team5461.robot;

import java.util.Vector;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class MultiPIDSubsystem extends PIDSubsystem {
	Vector<PIDController> m_controllers = new Vector<>();
	private double m_right;
	public MultiPIDSubsystem(double p, double i, double d){
		super(p,i,d);
		addController(getPIDController());
		
		PIDSource pidSource = getNewPIDSource(SensorNumber.BottomRightProximitySensor);
		PIDOutput pidOutput = getNewPIDOutput(SensorNumber.BottomRightProximitySensor);
		PIDController controller = new PIDController(p, i, d, pidSource, pidOutput);
		addController(controller);
	}
	
	public final void addController(PIDController controller){
		if(controller == null){
			throw new NullPointerException();
		}
		m_controllers.addElement(controller);
	}
	
	@Override
	public void enable(){
		for (PIDController steve : m_controllers){
			steve.enable();
		}
	}
	
	@Override
	public void setSetpoint(double setpoint) {
		for (PIDController qwerty : m_controllers){
			qwerty.setSetpoint(setpoint);
		}
	}
	@Override
	public synchronized boolean onTarget(){
		boolean isOnTarget = true;
		for (PIDController asdf : m_controllers){
			isOnTarget = isOnTarget && asdf.onTarget();
			if (!isOnTarget){
				break;
			}
		}
		return isOnTarget;
	}

	@Override
	protected double returnPIDInput() {
		return returnPIDInput(SensorNumber.BottomLeftProximitySensor);
	}
	
	protected double returnPIDInput(SensorNumber sensorNumber) {
		return Robot.redRover.getDistanceFromSensor(sensorNumber);
	}
	
	@Override
	protected void usePIDOutput(double output) {
		Robot.drivetrain.drive(output, m_right);
	}
	
	protected void usePIDOutput(double output, SensorNumber sensorNumber) {
		switch(sensorNumber) {
			case BottomRightProximitySensor:
				m_right = output;
			default:
				m_right = 0.0;
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public PIDOutput getNewPIDOutput(SensorNumber sensorNumber){
		return new PIDOutput() {

		    public void pidWrite(double output) {
		      usePIDOutput(output, sensorNumber);
		    }
		};
	}
	
	public PIDSource getNewPIDSource(SensorNumber sensorNumber){
		return new PIDSource() {
		    public void setPIDSourceType(PIDSourceType pidSource) {}

		    public PIDSourceType getPIDSourceType() {
		      return PIDSourceType.kDisplacement;
		    }

		    public double pidGet() {
		      return returnPIDInput(sensorNumber);
		    }
		  };
	}

}
