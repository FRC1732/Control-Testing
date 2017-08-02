package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/*
 * Taken from Cheesypoofs 2016 code
 */
public class CheesyArcade extends ArcadeDrive {

    private final BooleanSupplier quickTurn;

    public CheesyArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput, BooleanSupplier quickTurn) {
	super(wheelInput, throttleInput);
	this.quickTurn = quickTurn;
    }

    double mQuickStopAccumulator;
    public static final double kThrottleDeadband = 0.02;
    private static final double kWheelDeadband = 0.02;
    private static final double kTurnSensitivity = 1.0;

    @Override
    public DriveOutput getOutput() {
	double wheel = handleDeadband(wheelInput.getAsDouble(), kWheelDeadband);
	double throttle = handleDeadband(throttleInput.getAsDouble(), kThrottleDeadband);
	boolean isQuickTurn = quickTurn.getAsBoolean();

	double overPower;

	double angularPower;

	if (isQuickTurn) {
	    if (Math.abs(throttle) < 0.2) {
		double alpha = 0.1;
		mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * limit(wheel, 1.0) * 2;
	    }
	    overPower = 1.0;
	    angularPower = wheel;
	} else {
	    overPower = 0.0;
	    angularPower = Math.abs(throttle) * wheel * kTurnSensitivity - mQuickStopAccumulator;
	    if (mQuickStopAccumulator > 1) {
		mQuickStopAccumulator -= 1;
	    } else if (mQuickStopAccumulator < -1) {
		mQuickStopAccumulator += 1;
	    } else {
		mQuickStopAccumulator = 0.0;
	    }
	}

	double rightPwm = throttle - angularPower;
	double leftPwm = throttle + angularPower;
	if (leftPwm > 1.0) {
	    rightPwm -= overPower * (leftPwm - 1.0);
	    leftPwm = 1.0;
	} else if (rightPwm > 1.0) {
	    leftPwm -= overPower * (rightPwm - 1.0);
	    rightPwm = 1.0;
	} else if (leftPwm < -1.0) {
	    rightPwm += overPower * (-1.0 - leftPwm);
	    leftPwm = -1.0;
	} else if (rightPwm < -1.0) {
	    leftPwm += overPower * (-1.0 - rightPwm);
	    rightPwm = -1.0;
	}
	return new DriveOutput(leftPwm, rightPwm);
    }

    private static double handleDeadband(double val, double deadband) {
	return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }

    private static double limit(double v, double limit) {
	return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

}