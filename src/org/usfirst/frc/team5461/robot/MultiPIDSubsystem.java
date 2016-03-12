package org.usfirst.frc.team5461.robot;

import java.util.Vector;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public abstract class MultiPIDSubsystem extends PIDSubsystem {
	private Vector<PIDController> m_controllers = new Vector<>();
	protected Vector<Double> m_results = new Vector<>();
	private PIDSourceType m_pidSourceType;
	
	public MultiPIDSubsystem(double p, double i, double d){
		super(p,i,d);
		addController(getPIDController());
	}
	
	public final void addController(PIDController controller, int position){
		if(controller == null){
			throw new NullPointerException();
		}
		m_controllers.add(position, controller);
	}
	
	public final void addController(PIDController controller){
		if(controller == null){
			throw new NullPointerException();
		}
		m_controllers.add(controller);
	}
	
	@Override
	public void enable(){
		for (PIDController steve : m_controllers){
			steve.enable();
		}
	}
	
	public void setSetPoint(double setpoint, int position) {
		try {
			m_controllers.get(position).setSetpoint(setpoint);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.getMessage();
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
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected double returnPIDInput() {
		return returnPIDInput(0);
	}
	
	protected void usePIDOutput(double output) {
		usePIDOutput(output, 0);
	}
	
	protected abstract double returnPIDInput(int position);
	
	protected void usePIDOutput(double output, int position) {
		m_results.set(position, output);
	}
	
	public void setInputRange(double minimumInput, double maximumInput) {
		for (PIDController controller : m_controllers) {
			controller.setInputRange(minimumInput, maximumInput);
		}
	}
	
	public void setOutputRange(double minimumOutput, double maximumOutput) {
		for (PIDController controller : m_controllers) {
			controller.setOutputRange(minimumOutput, maximumOutput);
		}
	}
	
	public PIDSource getNewPIDSource(int position) {
		return new PIDSource() {
		    public void setPIDSourceType(PIDSourceType pidSource) { }

		    public PIDSourceType getPIDSourceType() {
		      return MultiPIDSubsystem.this.getPIDSourceType();
		    }

		    public double pidGet() {
		      return returnPIDInput(position);
		    }
		  };
	}
	
	public PIDOutput getNewPIDOutput(int position) {
		return new PIDOutput() {

		    public void pidWrite(double output) {
		      usePIDOutput(output, position);
		    }
		};
	}
	
	protected void setPIDSourceType(PIDSourceType pidSourceType) {
		m_pidSourceType = pidSourceType;
	}
	
	protected PIDSourceType getPIDSourceType() {
		return m_pidSourceType;
	}
}
