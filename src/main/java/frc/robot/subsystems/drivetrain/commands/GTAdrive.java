package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

import static frc.robot.Constants.GTAdrive.*;
import static frc.robot.Ports.MILF.*;

public class GTAdrive extends CommandBase {
    private Joystick joystickLeft = new Joystick(JOYSTICK_LEFT);
    private Joystick joystickRight = new Joystick(JOYSTICK_RIGHT);
    private Drivetrain drivetrain;
    private double powerRightJoystick = 0;
    private double powerLeftJoystick = 0;

    public GTAdrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    private static boolean checkDeadBand(double power, double deadband) {
        if (power > -deadband && power < deadband)
            return true;
        return false;
    }

    @Override
    public void execute() {
        powerRightJoystick = joystickRight.getX();
        powerLeftJoystick = -joystickLeft.getY();

        if (checkDeadBand(powerLeftJoystick, DEAD_BAND)) {
            powerLeftJoystick = 0;
        }

        if (checkDeadBand(powerRightJoystick, DEAD_BAND)) {
            powerRightJoystick = 0;
        }

        powerRightJoystick *= 0.25;

        double finalPowerRight = powerLeftJoystick - powerRightJoystick;
        double finalPowerLeft = powerLeftJoystick + powerRightJoystick;

        finalPowerLeft = (!checkDeadBand(finalPowerLeft, 1)) ? Math.round(finalPowerLeft) : finalPowerLeft;
        finalPowerRight = (!checkDeadBand(finalPowerRight, 1)) ? Math.round(finalPowerRight) : finalPowerRight;

        drivetrain.setPowerR(finalPowerRight);
        drivetrain.setPowerL(finalPowerLeft);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
