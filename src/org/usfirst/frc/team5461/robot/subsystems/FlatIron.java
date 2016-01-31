package org.usfirst.frc.team5461.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

public class FlatIron  {
	private ADIS16448_IMU m_imu;
	private Double m_heading;
	private boolean m_isenabled=false;
	
	public FlatIron(ADIS16448_IMU imu){
		m_imu=imu;
		m_heading=0.0;
	}
	
	public Pair<Double>getAdjustmentFactors(){
	
		if (m_isenabled) {
			return new Pair<Double>(getLeftAdjustment(),getRightAdjustment());
		}
		else {
			return new Pair<Double>(1.0,1.0);
		}
	}
	
	private Double getLeftAdjustment(){
		return Math.sin((m_imu.getAngleZ()-m_heading)*Math.PI/180.0)+1.0;
			
	}
	private Double getRightAdjustment(){
		return Math.sin(-1.0*(m_imu.getAngleZ()-m_heading)*Math.PI/180.0)+1.0;
		}
	public  void setStartingValue(Double heading){
		m_heading=heading;
	}
	
	
	public static class Pair<T>{
		public T m_leftval;
		public T m_rightval;
		public Pair  (T leftval, T rightval){
			
			m_leftval= leftval;
			m_rightval=rightval;
		}
	}
	

}
