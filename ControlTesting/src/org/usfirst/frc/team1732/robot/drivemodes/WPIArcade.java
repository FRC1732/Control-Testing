package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

/*
 * Taken from the WPI FRC Library RobotDrive.class
 */
public class WPIArcade extends ArcadeDrive {

    public WPIArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput) {
	super(wheelInput, throttleInput);
    }

    public WPIArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput,
	    Function<Double, Double> wheelResponseCurve, Function<Double, Double> throttleResponseCurve) {
	super(wheelInput, throttleInput, wheelResponseCurve, throttleResponseCurve);
    }

    @Override
    public DriveOutput getOutput() {
	double wheel = wheelInput.getAsDouble();
	double throttle = throttleInput.getAsDouble();

	double left;
	double right;

	if (throttle > 0.0) {
	    if (wheel > 0.0) {
		left = throttle - wheel;
		right = Math.max(throttle, wheel);
	    } else {
		left = Math.max(throttle, -wheel);
		right = throttle + wheel;
	    }
	} else {
	    if (wheel > 0.0) {
		left = -Math.max(-throttle, wheel);
		right = throttle + wheel;
	    } else {
		left = throttle - wheel;
		right = -Math.max(-throttle, -wheel);
	    }
	}

	return new DriveOutput(left, right);
    }

}