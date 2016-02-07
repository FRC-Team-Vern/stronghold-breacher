package org.usfirst.frc.team5461.robot;

public enum SensorNumber {
	FrontLeftProximitySensor(0),
	FrontRightProximitySensor(1),
	BottomLeftProximitySensor(2),
	BottomRightProximitySensor(3);
	private int intValue;
	private SensorNumber(int value){
		intValue = value;
	}
public int getValue(){
	return intValue;
}
}