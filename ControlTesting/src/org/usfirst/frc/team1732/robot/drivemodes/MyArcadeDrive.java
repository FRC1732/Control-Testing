package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class MyArcadeDrive extends ArcadeDrive {

    public MyArcadeDrive(DoubleSupplier wheelInput, DoubleSupplier throttleInput,
	    Function<Double, Double> wheelIOMapper, Function<Double, Double> throttleIOMapper) {
	super(wheelInput, throttleInput, wheelIOMapper, throttleIOMapper);
    }

    public MyArcadeDrive(DoubleSupplier wheelInput, DoubleSupplier throttleInput) {
	super(wheelInput, throttleInput);
    }

    @Override
    public DriveOutput getOutput() {
	double angle = wheelIOMapper.apply(wheelInput.getAsDouble());
	double throttle = throttleIOMapper.apply(throttleInput.getAsDouble());
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