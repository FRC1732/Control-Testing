package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public abstract class ArcadeDrive extends DriveMode {

    protected final DoubleSupplier wheelInput;
    protected final DoubleSupplier throttleInput;
    protected final Function<Double, Double> wheelIOMapper;
    protected final Function<Double, Double> throttleIOMapper;

    public ArcadeDrive(DoubleSupplier wheelInput, DoubleSupplier throttleInput) {
	this(wheelInput, throttleInput, wheel -> wheel, throttle -> throttle);
    }

    public ArcadeDrive(DoubleSupplier wheelInput, DoubleSupplier throttleInput, Function<Double, Double> wheelIOMapper,
	    Function<Double, Double> throttleIOMapper) {
	this.wheelInput = wheelInput;
	this.throttleInput = throttleInput;
	this.wheelIOMapper = wheelIOMapper;
	this.throttleIOMapper = throttleIOMapper;
    }

}
