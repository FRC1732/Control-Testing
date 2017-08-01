package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class TankDrive extends DriveMode {

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;
    private Function<Double, Double> responseCurve;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput) {
	/*
	 * input -> input makes the same thing as in this comment
	 * 
	 * Function<Double, Double> inputOutputMapper = (input) -> { return
	 * input; };
	 */
	this(leftInput, rightInput, input -> input);
    }

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, Function<Double, Double> responseCurve) {
	this.responseCurve = responseCurve;
    }

    @Override
    public DriveOutput getOutput() {
	double left = responseCurve.apply(leftInput.getAsDouble());
	double right = responseCurve.apply(rightInput.getAsDouble());
	return new DriveOutput(left, right);
    }

}
