package org.usfirst.frc.team5461.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

public class FlatIron  {
	private ADIS16448_IMU m_imu;
	
	
	public FlatIron(ADIS16448_IMU imu){
		m_imu=imu;
	}
	
	public Pair<Double>getAdjustmentFactors(){
		return null;
		
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
