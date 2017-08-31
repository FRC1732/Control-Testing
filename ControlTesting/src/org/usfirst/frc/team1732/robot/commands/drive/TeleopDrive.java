package org.usfirst.frc.team1732.robot.commands.drive;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode;
import org.usfirst.frc.team1732.robot.drivemodes.DriveMode.DriveOutput;
import org.usfirst.frc.team1732.robot.drivemodes.TankDrive;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

    private static DriveMode driveMode = new TankDrive(Robot.oi.controller()::getLeftY,
	    Robot.oi.controller()::getRightY);

    public TeleopDrive() {
	super("TelopDrive");
	requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {
	Robot.drivetrain.setControlMode(driveMode.getControlMode());
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
