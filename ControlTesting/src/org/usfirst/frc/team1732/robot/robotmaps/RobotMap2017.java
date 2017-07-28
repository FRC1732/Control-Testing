package org.usfirst.frc.team1732.robot.robotmaps;

import org.usfirst.frc.team1732.robot.controllers.TankController;

public abstract class RobotMap2017 extends RobotMap {

    private TankController controller;

    public RobotMap2017(TankController controller) {
	this.controller = controller;
    }

    public int getLeftJoystickUSB() {
	return controller.getLeftUSB();
    }

    public int getRightJoystickUSB() {
	return controller.getRightUSB();
    }

    public int getRightAxis() {
	return controller.getRightAxis();
    }

    public int getLeftAxis() {
	return controller.getLeftAxis();
    }

    @Override
    public int getPCM_CAN_ID() {
	return 0;
    }

    public int getLeftMasterMotorDeviceNumber() {
	return 5;
    }

    public int getLeft1MotorDeviceNumber() {
	return 6;
    }

    public int getLeft2MotorDeviceNumber() {
	return 7;
    }

    public int getRightMasterMotorDeviceNumber() {
	return 0;
    }

    public int getRight1MotorDeviceNumber() {
	return 1;
    }

    public int getRight2MotorDeviceNumber() {
	return 2;
    }

    public int getShifterSolenoidDeviceNumber() {
	return 0;
    }

}