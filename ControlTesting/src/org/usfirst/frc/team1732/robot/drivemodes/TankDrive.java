package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class TankDrive extends DriveMode {

    private DoubleSupplier leftInput;
    private DoubleSupplier rightInput;
    private Function<Double, Double> IOMapper;

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput) {
	/*
	 * input -> input makes the same thing as in this comment
	 * 
	 * Function<Double, Double> inputOutputMapper = (input) -> { return
	 * input; };
	 */
	this(leftInput, rightInput, input -> input);
    }

    public TankDrive(DoubleSupplier leftInput, DoubleSupplier rightInput, Function<Double, Double> IOMapper) {
	this.IOMapper = IOMapper;
    }

    @Override
    public DriveOutput getOutput() {
	double left = IOMapper.apply(leftInput.getAsDouble());
	double right = IOMapper.apply(rightInput.getAsDouble());
	return new DriveOutput(left, right);
    }

}
