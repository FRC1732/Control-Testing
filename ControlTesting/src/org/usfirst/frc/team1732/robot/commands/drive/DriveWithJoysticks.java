package org.usfirst.frc.team1732.robot.commands.drive;

import org.usfirst.frc.team1732.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
	super("Drive With Joysticks");
	requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {
	if (Robot.drivetrain.isInMotionMagicMode())
	    Robot.drivetrain.changeToPercentVBus();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
	double left = Robot.oi.getLeftSpeed();
	double right = Robot.oi.getRightSpeed();
	Robot.drivetrain.driveWithJoysticks(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return false;
    }
}
