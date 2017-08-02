package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class MyArcade extends ArcadeDrive {

    public MyArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput,
	    Function<Double, Double> wheelResponseCurve, Function<Double, Double> throttleResponseCurve) {
	super(wheelInput, throttleInput, wheelResponseCurve, throttleResponseCurve);
    }

    public MyArcade(DoubleSupplier wheelInput, DoubleSupplier throttleInput) {
	super(wheelInput, throttleInput);
    }

    @Override
    public DriveOutput getOutput() {
	double angle = wheelInput.getAsDouble();
	double throttle = throttleInput.getAsDouble();
	double left = throttle + angle;
	double right = throttle - angle;
	double max = Math.max(Math.abs(left), Math.abs(right));
	if (max > 1) {
	    left = left / max;
	    right = right / max;
	}
	return new DriveOutput(left, right);
    }

}