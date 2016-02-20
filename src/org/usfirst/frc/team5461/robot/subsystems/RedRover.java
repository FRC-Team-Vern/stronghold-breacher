package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.SensorNumber;
import org.usfirst.frc.team5461.sensors.VL6180x;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RedRover extends Subsystem {
	final static int vl6180xAddress=0x29;
	final static int SELECT_ADDRESS_START = 0;
	final static int SELECT_ADDRESS_SIZE = 4;
	DigitalOutput[] sa;
	VL6180x proximitySensor;
	public RedRover() {
		sa = new DigitalOutput[SELECT_ADDRESS_SIZE];
		for (int i=0; i<SELECT_ADDRESS_SIZE; ++i) {
			sa[i] = new DigitalOutput(i+SELECT_ADDRESS_START);
		}
		
		proximitySensor = new VL6180x(vl6180xAddress);
	
		initAllVL6180x();
		allDefaultSettings();
	}

	private void initAllVL6180x() {
		for (SensorNumber sn : SensorNumber.values()) {
			callSensor(sn);
			if(proximitySensor.VL6180xInit() != 0)
			{
				System.out.println("Failure to initialize proximity sensor: " + sn.getValue());
			}
		}
	}
	
	private void allDefaultSettings() {
		for (SensorNumber sn : SensorNumber.values()) {
			callSensor(sn);
			proximitySensor.defaultSettings();
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void callSensor(SensorNumber sensorNumber) {
    	for (int i=0; i<SELECT_ADDRESS_SIZE; ++i) {
    		boolean setValue = (((sensorNumber.getValue() >> i) & 0x01) == 1);
    		sa[i].set(setValue);
    	}
    
    	try {
			Thread.sleep(10);
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public int getDistanceFromSensor(SensorNumber sensorNumber) {
    	callSensor(sensorNumber);
    	return proximitySensor.getDistance();
    }
    
    public int[] getDistanceFromAllSensors() {
    	int[] results = new int[SensorNumber.values().length];
    	for (SensorNumber sensor : SensorNumber.values()){
    		results[sensor.getValue()] = getDistanceFromSensor(sensor);
    	}
    	return results;
    }
    
    public void log() {
    	int[] distances = getDistanceFromAllSensors();
    	for ( int i = 0; i<distances.length; ++i ) {
    		SmartDashboard.putNumber("Distance " + Integer.toString(i), distances[i]);
    	}
    }
}

