package org.usfirst.frc.team1732.robot.drivemodes;

import com.ctre.CANTalon.TalonControlMode;

/**
 * Super class for implementing different drive modes. <br>
 * Only meant for skid-steer type robots
 */
public abstract class DriveMode {

    TalonControlMode controlMode;

    public DriveMode() {
	this(TalonControlMode.Voltage);
    }

    public DriveMode(TalonControlMode mode) {
	this.controlMode = mode;
    }

    public static class DriveOutput {
	public final double left;
	public final double right;

	public DriveOutput(double l, double r) {
	    left = l;
	    right = r;
	}
    }

    public abstract DriveOutput getOutput();

    public TalonControlMode getControlMode() {
	return controlMode;
    }

}