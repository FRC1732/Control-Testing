package org.usfirst.frc.team1732.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DriveController {

    private final int leftUSB;
    private final int leftX;
    private final int leftY;
    private final int leftTrigger;

    private final int rightUSB;
    private final int rightX;
    private final int rightY;
    private final int rightTrigger;

    public final boolean isTriggerAxis;

    public final Joystick leftStick;
    public final Joystick rightStick;

    public final JoystickButton leftTriggerButton;
    public final JoystickButton rightTriggerButton;

    public DriveController(int leftUSB, int rightUSB, int xAxis, int yAxis, int trigger, boolean isTriggerAxis) {
	this(leftUSB, rightUSB, xAxis, yAxis, trigger, xAxis, yAxis, trigger, isTriggerAxis);
    }

    public DriveController(int USB, int leftX, int leftY, int leftTrigger, int rightX, int rightY, int rightTrigger,
	    boolean isTriggerAxis) {
	this(USB, USB, leftX, leftY, leftTrigger, rightX, rightY, rightTrigger, isTriggerAxis);
    }

    public DriveController(int leftUSB, int rightUSB, int leftX, int leftY, int leftTrigger, int rightX, int rightY,
	    int rightTrigger, boolean isTriggerAxis) {
	this.leftUSB = leftUSB;
	this.leftX = leftX;
	this.leftY = leftY;
	this.leftTrigger = leftTrigger;
	this.rightUSB = rightUSB;
	this.rightX = rightX;
	this.rightY = rightY;
	this.rightTrigger = rightTrigger;
	this.isTriggerAxis = isTriggerAxis;

	leftStick = new Joystick(leftUSB);
	rightStick = new Joystick(rightUSB);

	if (isTriggerAxis) {
	    leftTriggerButton = null;
	    rightTriggerButton = null;
	} else {
	    leftTriggerButton = new JoystickButton(leftStick, leftTrigger);
	    rightTriggerButton = new JoystickButton(rightStick, rightTrigger);
	}
    }

    public double getLeftX() {
	return leftStick.getRawAxis(leftX);
    }

    public double getLeftY() {
	return leftStick.getRawAxis(leftY);
    }

    public double getLeftTrigger() {
	if (isTriggerAxis) {
	    return leftStick.getRawAxis(leftTrigger);
	} else {
	    return 0;
	}
    }

    public double getRightX() {
	return rightStick.getRawAxis(rightX);
    }

    public double getRightY() {
	return rightStick.getRawAxis(rightY);
    }

    public double getRightTrigger() {
	if (isTriggerAxis) {
	    return rightStick.getRawAxis(rightTrigger);
	} else {
	    return 0;
	}
    }

}