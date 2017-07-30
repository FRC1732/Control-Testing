package org.usfirst.frc.team1732.robot.drivemodes;

import java.util.function.Function;

import org.usfirst.frc.team1732.robot.oi.DriveController;

public abstract class ArcadeDrive extends DriveMode {

    protected final Function<Double, Double> angleInputOutputMapper;
    protected final Function<Double, Double> throttleInputOutputMapper;

    public ArcadeDrive(DriveController controller, Function<Double, Double> angleInputOutputMapper,
	    Function<Double, Double> throttleInputOutputMapper) {
	super(controller);
	this.angleInputOutputMapper = angleInputOutputMapper;
	this.throttleInputOutputMapper = throttleInputOutputMapper;
    }

}
