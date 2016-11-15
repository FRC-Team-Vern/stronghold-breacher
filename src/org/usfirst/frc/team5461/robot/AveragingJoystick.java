package org.usfirst.frc.team5461.robot;

import java.util.ArrayDeque;

import edu.wpi.first.wpilibj.Joystick;

public class AveragingJoystick extends Joystick {
	
	private static final int AVERAGE_SIZE = 10;
	private ArrayDeque<Double> mLeftJoyStack = new ArrayDeque<>();
	private ArrayDeque<Double> mRightJoyStack = new ArrayDeque<>();

	public AveragingJoystick(int port) {
		super(port);
		initializeLeftRightStacks();
	}
	
	private void initializeLeftRightStacks() {
		for (int i=0; i<AVERAGE_SIZE; ++i) {
			mLeftJoyStack.add(0.0);
			mRightJoyStack.add(0.0);
		}
	}
	
	@Override
	public double getRawAxis(int axis) {
		double returnVal = super.getRawAxis(axis);
		if (axis == Joystick.AxisType.kY.value) {  // Left Value
//			// Update left and right values
			
			double currentLeftVal = -super.getRawAxis(Joystick.AxisType.kY.value);
//			System.out.println("Left Val: " + Double.toString(currentLeftVal));
//			mLeftJoyStack.add(currentLeftVal);
//			mLeftJoyStack.pop();
			double currentRightVal = -super.getRawAxis(Joystick.AxisType.kTwist.value);
//			System.out.println("Right Val: " + Double.toString(currentRightVal));
//			mRightJoyStack.add(currentRightVal);
//			mRightJoyStack.pop();
//
//			// return average left value
//			double leftSum = 0.0;
//			for (double val: mLeftJoyStack) {
//				leftSum += val;
//			}
//			returnVal = leftSum / mLeftJoyStack.size();
		} else if (axis == Joystick.AxisType.kTwist.value) { // Right Value
//			// return left value
//			double rightSum = 0.0;
//			for (double val: mRightJoyStack) {
//				rightSum += val;
//			}
//			returnVal = rightSum / mRightJoyStack.size();
		}
		return returnVal;
	}
}
