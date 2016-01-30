package org.usfirst.frc.team5461.sensors;

public enum vl6180x_as_gain
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
	private static java.util.HashMap<Integer, vl6180x_as_gain> mappings;
	private static java.util.HashMap<Integer, vl6180x_as_gain> getMappings()
	{
		if (mappings == null)
		{
			synchronized (vl6180x_as_gain.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, vl6180x_as_gain>();
				}
			}
		}
		return mappings;
	}

	private vl6180x_as_gain(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static vl6180x_as_gain forValue(int value)
	{
		return getMappings().get(value);
	}
}