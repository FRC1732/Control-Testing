package org.usfirst.frc.team1732.robot.commands.drive;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode.DriveOutput;
import org.usfirst.frc.team1732.robot.drivemodes.TankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

    public static final List<DriveMode> driveModes = Collections
	    .unmodifiableList(Arrays.asList(new TankDrive(Robot.oi.controller(), input -> input),
		    new TankDrive(Robot.oi.controller(), x -> x * Math.abs(x)),
		    new TankDrive(Robot.oi.controller(), x -> Math.pow(x, 3)),
		    new TankDrive(Robot.oi.controller(), x -> Math.signum(x) * Math.pow(Math.abs(x), Math.abs(x))),
		    new TankDrive(Robot.oi.controller(), x -> Math.sin(x * Math.PI / 2)),
		    new TankDrive(Robot.oi.controller(), x -> Math.pow(x, 1.0 / 3.0))));

    private static DriveMode driveMode;

    public static void setDriveMode(DriveMode mode) {
	driveMode = mode;
    }

    public TeleopDrive() {
	super("TelopDrive");
	requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	DriveOutput o = driveMode.getOutput();
	Robot.drivetrain.drive(o.left, o.right);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return false;
    }
}
