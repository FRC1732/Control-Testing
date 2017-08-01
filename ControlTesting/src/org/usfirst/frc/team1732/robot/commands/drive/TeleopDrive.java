package org.usfirst.frc.team1732.robot.commands.drive;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode.DriveOutput;
import org.usfirst.frc.team1732.robot.drivemodes.TankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

    public static final List<DriveMode> driveModes;

    static {
	DoubleSupplier leftY = Robot.oi.controller()::getLeftY;
	DoubleSupplier rightY = Robot.oi.controller()::getRightY;
	// add inverse deadband stuff
	Function<Double, Double> a = x -> x;
	Function<Double, Double> b = x -> Math.pow(x, 3); // add ether's
							  // parameters

	driveModes = Collections
		.unmodifiableList(Arrays.asList(new TankDrive(leftY, rightY, a), new TankDrive(leftY, rightY, b)));
    }

    private static DriveMode driveMode = driveModes.get(0);

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
