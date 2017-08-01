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

    public final double joystickDeadband;
    public final double triggerDeadband;

    /**
     * Constructor meant to use with the dual joysticks <br>
     * Assumes that both joysticks have same axis numbers
     * 
     * @param leftUSB
     *            left USB number
     * @param rightUSB
     *            right USB number
     * @param xAxis
     *            joystick x axis number
     * @param yAxis
     *            joystick y axis number
     * @param trigger
     *            trigger axis/button number
     * @param isTriggerAxis
     *            if the trigger is an axis (range of values) or just a button
     *            (on and off)
     */
    public DriveController(int leftUSB, int rightUSB, int xAxis, int yAxis, int trigger, boolean isTriggerAxis) {
	this(leftUSB, rightUSB, xAxis, yAxis, trigger, xAxis, yAxis, trigger, isTriggerAxis, 0, 0);
    }

    /**
     * Constructor meant to use with the dual joysticks <br>
     * Assumes that both joysticks have same axis numbers
     * 
     * @param leftUSB
     *            left USB number
     * @param rightUSB
     *            right USB number
     * @param xAxis
     *            joystick x axis number
     * @param yAxis
     *            joystick y axis number
     * @param trigger
     *            trigger axis/button number
     * @param isTriggerAxis
     *            if the trigger is an axis (range of values) or just a button
     *            (on and off)
     */
    public DriveController(int leftUSB, int rightUSB, int xAxis, int yAxis, int trigger, boolean isTriggerAxis,
	    double joystickDeadband, double triggerDeadband) {
	this(leftUSB, rightUSB, xAxis, yAxis, trigger, xAxis, yAxis, trigger, isTriggerAxis, joystickDeadband,
		triggerDeadband);
    }

    /**
     * Constructor meant for game controllers
     * 
     * @param USB
     *            the single USB port for the controller
     * @param leftX
     *            left x axis number
     * @param leftY
     *            left y axis number
     * @param leftTrigger
     *            left trigger axis/button number
     * @param rightX
     *            right x axis number
     * @param rightY
     *            right x axis number
     * @param rightTrigger
     *            right trigger axis/button number
     * @param isTriggerAxis
     *            if the trigger is an axis (range of values) or just a button
     *            (on and off)
     * @param joystickDeadband
     *            range of joystick output in which a value of 0 should be
     *            reported
     * @param triggerDeadband
     *            if trigger is an axis, the range of trigger output in which a
     *            value of 0 should be reported
     */
    public DriveController(int USB, int leftX, int leftY, int leftTrigger, int rightX, int rightY, int rightTrigger,
	    boolean isTriggerAxis) {
	this(USB, USB, leftX, leftY, leftTrigger, rightX, rightY, rightTrigger, isTriggerAxis, 0, 0);
    }

    /**
     * Constructor meant for game controllers
     * 
     * @param USB
     *            the single USB port for the controller
     * @param leftX
     *            left x axis number
     * @param leftY
     *            left y axis number
     * @param leftTrigger
     *            left trigger axis/button number
     * @param rightX
     *            right x axis number
     * @param rightY
     *            right x axis number
     * @param rightTrigger
     *            right trigger axis/button number
     * @param isTriggerAxis
     *            if the trigger is an axis (range of values) or just a button
     *            (on and off)
     * @param joystickDeadband
     *            range of joystick output in which a value of 0 should be
     *            reported
     * @param triggerDeadband
     *            if trigger is an axis, the range of trigger output in which a
     *            value of 0 should be reported
     */
    public DriveController(int USB, int leftX, int leftY, int leftTrigger, int rightX, int rightY, int rightTrigger,
	    boolean isTriggerAxis, double joystickDeadband, double triggerDeadband) {
	this(USB, USB, leftX, leftY, leftTrigger, rightX, rightY, rightTrigger, isTriggerAxis, joystickDeadband,
		triggerDeadband);
    }

    /**
     * Constructor with all arguments. Generally one of the other two
     * constructors should be used.
     * 
     * @param leftUSB
     *            left USB number
     * @param rightUSB
     *            right USB number
     * @param leftX
     *            left x axis number
     * @param leftY
     *            left y axis number
     * @param leftTrigger
     *            left trigger axis/button number
     * @param rightX
     *            right x axis number
     * @param rightY
     *            right x axis number
     * @param rightTrigger
     *            right trigger axis/button number
     * @param isTriggerAxis
     *            if the trigger is an axis (range of values) or just a button
     *            (on and off)
     */
    public DriveController(int leftUSB, int rightUSB, int leftX, int leftY, int leftTrigger, int rightX, int rightY,
	    int rightTrigger, boolean isTriggerAxis, double joystickDeadband, double triggerDeadband) {
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

	this.triggerDeadband = triggerDeadband;
	this.joystickDeadband = joystickDeadband;
    }

    public double getLeftX() {
	double d = leftStick.getRawAxis(leftX);
	return Math.abs(d) < joystickDeadband ? 0 : d;
    }

    public double getLeftY() {
	double d = leftStick.getRawAxis(leftY);
	return Math.abs(d) < joystickDeadband ? 0 : d;
    }

    public double getLeftTrigger() {
	if (isTriggerAxis) {
	    double d = leftStick.getRawAxis(leftTrigger);
	    return Math.abs(d) < triggerDeadband ? 0 : d;
	} else {
	    return 0;
	}
    }

    public double getRightX() {
	double d = rightStick.getRawAxis(rightX);
	return Math.abs(d) < joystickDeadband ? 0 : d;
    }

    public double getRightY() {
	double d = rightStick.getRawAxis(rightY);
	return Math.abs(d) < joystickDeadband ? 0 : d;
    }

    public double getRightTrigger() {
	if (isTriggerAxis) {
	    double d = rightStick.getRawAxis(rightTrigger);
	    return Math.abs(d) < triggerDeadband ? 0 : d;
	} else {
	    return 0;
	}
    }

}