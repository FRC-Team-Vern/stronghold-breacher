package org.usfirst.frc.team5461.sensors;

public enum VL6180xALSGain
{ //Data sheet shows gain values as binary list

GAIN_20(0), // Actual ALS Gain of 20
GAIN_10(1), // Actual ALS Gain of 10.32
GAIN_5(2), // Actual ALS Gain of 5.21
GAIN_2_5(3), // Actual ALS Gain of 2.60
GAIN_1_67(4), // Actual ALS Gain of 1.72
GAIN_1_25(5), // Actual ALS Gain of 1.28
GAIN_1(6), // Actual ALS Gain of 1.01
GAIN_40(7); // Actual ALS Gain of 40

	private int intValue;
	private static java.util.HashMap<Integer, VL6180xALSGain> mappings;
	private static java.util.HashMap<Integer, VL6180xALSGain> getMappings()
	{
		if (mappings == null)
		{
			synchronized (VL6180xALSGain.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, VL6180xALSGain>();
				}
			}
		}
		return mappings;
	}

	private VL6180xALSGain(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static VL6180xALSGain forValue(int value)
	{
		return getMappings().get(value);
	}
}