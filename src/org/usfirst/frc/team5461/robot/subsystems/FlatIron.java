package org.usfirst.frc.team5461.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlatIron extends Subsystem {
	private ADIS16448_IMU m_imu;
	private Double m_heading;
	private boolean m_isEnabled=false;
	
	
	public FlatIron(){
		m_imu=new ADIS16448_IMU();
		m_heading=0.0;
	}
	public void enable(){
		setStartingValue(m_imu.getAngleZ());
		m_isEnabled= true;
		
	}
	public void disable(){
		m_isEnabled=false;
	}
	
	public Pair<Double>getAdjustmentFactors(){
	
		if (m_isEnabled) {
			return new Pair<Double>(getLeftAdjustment(),getRightAdjustment());
		}
		else {
			return new Pair<Double>(1.0,1.0);
		}
	}
	
	private Double getLeftAdjustment(){
		return Math.sin(-1.0*(m_imu.getAngleZ()-m_heading)*Math.PI/180.0)+1.0;
			
	}
	private Double getRightAdjustment(){
		return Math.sin((m_imu.getAngleZ()-m_heading)*Math.PI/180.0)+1.0;
		}
	public  void setStartingValue(Double heading){
		m_heading=heading;
	}
	
	public double getImuZValue(){
	return m_imu.getAngleZ();
}

public double getImuXValue(){
	return m_imu.getAngleX();
}

public double getImuYValue(){
	return m_imu.getAngleY();
}

	public static class Pair<T>{
		public T m_leftval;
		public T m_rightval;
		public Pair  (T leftval, T rightval){
			
			m_leftval= leftval;
			m_rightval=rightval;
		}
	}


	@Override
	protected void initDefaultCommand() {
		//do nothing
		
	}
	
public void log(){
	SmartDashboard.putBoolean("enabled",m_isEnabled);
	SmartDashboard.putNumber("current z axis",m_imu.getAngleZ());
	SmartDashboard.putNumber("current heading", m_heading);
}
}
