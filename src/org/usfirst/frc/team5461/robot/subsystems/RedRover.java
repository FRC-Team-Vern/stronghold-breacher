package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.SensorNumber;
import org.usfirst.frc.team5461.sensors.VL6180x;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RedRover extends Subsystem {
	final static int vl6180xAddress=0x29;
    
	DigitalOutput s0;
	DigitalOutput s1;
	VL6180x proximitySensor;
	public RedRover(){
		s0 = new DigitalOutput(0);
		s1 = new DigitalOutput(1);
		proximitySensor = new VL6180x(vl6180xAddress);
		
		if(proximitySensor.VL6180xInit() != 0)
		{
		System.out.println	("Failure to initialize proximity sensor.");
		}
   
		proximitySensor.defaultSettings();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void callSensor(SensorNumber sensorNumber){
    	s0.set((sensorNumber.getValue() & 0x01) == 1);
    	s1.set((sensorNumber.getValue() & 0x02) == 1 );
    	try {
			Thread.sleep(10);
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	}
    public int getDistanceFromSensor(SensorNumber sensorNumber){
    	callSensor(sensorNumber);
    	return proximitySensor.getDistance();
    }
    public int[] getDistanceFromAllSensors(){
    	int[] results = new int[SensorNumber.values().length];
    	for (SensorNumber sensor : SensorNumber.values()){
    		results[sensor.getValue()] = getDistanceFromSensor(sensor);
    	}
    	return results;
    }
    }

