package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

/*
 * Based on the cheesy poofs 2016 code, and team 111's code which
 * apparently automates the quick turn (activate it at low speeds)
 * (but I didn't find it on their github, only read about that)
 */

public class HaloArcade extends ArcadeDrive {

    public HaloArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput, Function<Double, Double> wheelIOMapper,
	    Function<Double, Double> throttleIOMapper) {
	super(wheelInput, throttleInput, wheelIOMapper, throttleIOMapper);
    }

    public HaloArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput) {
	this(wheelInput, throttleInput, wheel -> wheel, throttle -> throttle);
    }

    double mQuickStopAccumulator;
    public static final double kThrottleDeadband = 0.04;
    private static final double kWheelDeadband = 0.02;
    private static final double kTurnSensitivity = 1.0;

    @Override
    public DriveOutput getOutput() {
	double wheel = handleDeadband(wheelInput.getAsDouble(), kWheelDeadband);
	double throttle = handleDeadband(throttleInput.getAsDouble(), kThrottleDeadband);
	boolean isQuickTurn = throttleInput.getAsDouble() < 0.04;

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