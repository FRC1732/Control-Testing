package org.usfirst.frc.team1732.robot.commands.joystickcommands;

import org.usfirst.frc.team1732.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleDriveMode extends Command {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
	if (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl())
	    Robot.drivetrain.changeToVoltageMode();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
	if (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl())
	    Robot.drivetrain.changeToPercentVBus();
    }

}
