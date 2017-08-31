package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class TankDrive extends DriveMode {

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput) {
	this(leftInput, rightInput, input -> input);
    }

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, Function<Double, Double> responseCurve) {
	this.leftInput = () -> responseCurve.apply(leftInput.getAsDouble());
	this.rightInput = () -> responseCurve.apply(rightInput.getAsDouble());
    }

    @Override
    public DriveOutput getOutput() {
	double left = leftInput.getAsDouble();
	double right = rightInput.getAsDouble();
	return new DriveOutput(left, right);
    }

}
