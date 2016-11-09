package org.usfirst.frc.team5461.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlatIron extends Subsystem {
	private ADIS16448_IMU m_imu;
	private Double m_headingZ;
	private boolean m_isEnabled=false;
	private boolean m_isInitialized = false;
	private Double m_headingX;
	public FlatIron() {
		m_imu=new ADIS16448_IMU();
		m_headingZ=0.0;
	}
	public void enable() {
		if (!m_isInitialized) {
		setStartingValue(m_imu.getAngleZ(), m_imu.getAngleX());
		m_isEnabled= true;
		m_isInitialized = true;
		}

	}
	public void disable() {
		m_isEnabled=false;
		m_isInitialized = false;
	}

	public Pair<Double>getAdjustmentFactors() {

		if (m_isEnabled) {
		//System.out.println(getTippingAdjustment());
			return new Pair<Double>(getLeftAdjustment(),getRightAdjustment());
		}
		else {
			return new Pair<Double>(1.0,1.0);
		}
	}

	private Double getLeftAdjustment() {
		double leftAdj = Math.sin((-1.0*(m_imu.getAngleZ()-m_headingZ))*Math.PI/180.0)+1.0;
		return leftAdj * getTippingAdjustment();
		
	}
	private Double getTippingAdjustment() {
		return Math.cos(2.0*(m_imu.getAngleX()-m_headingX)*Math.PI/180.0);
		
	}
	
	private Double getRightAdjustment() {
		double rightAdj = Math.sin((m_imu.getAngleZ()-m_headingZ)*Math.PI/180.0)+1.0;
		return rightAdj * getTippingAdjustment();
	}
	
	public  void setStartingValue(Double headingZ,Double headingX) {
		m_headingZ=headingZ;
		m_headingX=headingX;
	}
 
	public double getImuZValue() {
		return m_imu.getAngleZ();
	}

	public double getImuXValue() {
		return m_imu.getAngleX();
	}

	public double getImuYValue() {
		return m_imu.getAngleY();
	}

	public static class Pair<T> {
		public T m_leftval;
		public T m_rightval;
		public Pair  (T leftval, T rightval){

			m_leftval= leftval;
			m_rightval=rightval;
		}
	}
	
	public boolean getIsEnabled() {
		return m_isEnabled;
	}
	public boolean getIsInitialized() {
		return m_isInitialized;
	}
	
	public void setIsInitialized(boolean isInitialized) {
		m_isInitialized = isInitialized;
	}
	

	@Override
	protected void initDefaultCommand() {
		/* no op*/
	}

	public void log(){
		SmartDashboard.putBoolean("FlatIron Enabled", m_isEnabled);
	}
}
