package org.usfirst.frc.team1732.robot.drivemodes;

/**
 * Super class for implementing different drive modes. <br>
 * Only meant for skid-steer type robots
 */
public abstract class DriveMode {

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