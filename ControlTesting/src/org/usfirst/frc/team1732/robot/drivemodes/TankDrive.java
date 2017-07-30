package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.Function;

import org.usfirst.frc.team1732.robot.oi.DriveController;

public class TankDrive extends DriveMode {

    private Function<Double, Double> inputOutputMapper;

    public TankDrive(DriveController controller) {
	/*
	 * input -> input does same thing as in this comment
	 * 
	 * Function<Double, Double> inputOutputMapper = (input) -> { return
	 * input; };
	 */
	this(controller, input -> input);
    }

    public TankDrive(DriveController controller, Function<Double, Double> inputOutputMapper) {
	super(controller);
	this.inputOutputMapper = inputOutputMapper;
    }

    @Override
    public DriveOutput getOutput() {
	double left = inputOutputMapper.apply(controller.getLeftY());
	double right = inputOutputMapper.apply(controller.getRightY());
	return new DriveOutput(left, right);
    }

}
