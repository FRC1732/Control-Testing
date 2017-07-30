package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.Function;

import org.usfirst.frc.team1732.robot.oi.DriveController;

public class MyArcadeDrive extends ArcadeDrive {

    public MyArcadeDrive(DriveController controller, Function<Double, Double> angleInputOutputMapper,
	    Function<Double, Double> throttleInputOutputMapper) {
	super(controller, angleInputOutputMapper, throttleInputOutputMapper);
    }

    @Override
    public DriveOutput getOutput() {
	double angle = angleInputOutputMapper.apply(controller.getLeftX());
	double throttle = throttleInputOutputMapper.apply(controller.getRightY());
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