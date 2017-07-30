package org.usfirst.frc.team1732.robot.drivemodes;

import org.usfirst.frc.team1732.robot.oi.DriveController;

/**
 * Super class for implementing different drive modes. <br>
 * Only meant for skid-steer type robots
 */
public abstract class DriveMode {

    protected final DriveController controller;

    public DriveMode(DriveController controller) {
	this.controller = controller;
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

}