package org.usfirst.frc.team1732.robot.oi;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.commands.joystickcommands.PrintDriveData;
import org.usfirst.frc.team1732.robot.commands.joystickcommands.ToggleDriveMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick left = new Joystick(Robot.robotMap.getLeftJoystickUSB());
    private Joystick right = new Joystick(Robot.robotMap.getRightJoystickUSB());

    private final int trigger = 1;
    private JoystickButton drivingMode = new JoystickButton(left, trigger);
    private JoystickButton printData = new JoystickButton(right, trigger);

    public OI() {
	drivingMode.toggleWhenPressed(new ToggleDriveMode());
	printData.toggleWhenPressed(new PrintDriveData());
    }

    public double getLeftSpeed() {
	return left.getY();
    }

    public double getRightSpeed() {
	return right.getY();
    }
}
