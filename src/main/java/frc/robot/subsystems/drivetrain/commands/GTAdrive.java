package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class GTAdrive extends CommandBase {
    private final Joystick joystickLeft = new Joystick(1);
    private final Joystick joystickRight = new Joystick(2);
    private final Drivetrain drivetrain;

    public GTAdrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    private static boolean checkDeadBand(double power, double deadband) {
        return power > -deadband && power < deadband;
    }

    @Override
    public void execute() {
        double powerRightJoystick = joystickRight.getX();
        double powerLeftJoystick = -joystickLeft.getY();

        if (checkDeadBand(powerLeftJoystick, 0.05)) {
            powerLeftJoystick = 0;
        }

        if (checkDeadBand(powerRightJoystick, 0.05)) {
            powerRightJoystick = 0;
        }

        powerRightJoystick *= 0.25;

        double finalPowerRight = powerLeftJoystick - powerRightJoystick;
        double finalPowerLeft = powerLeftJoystick + powerRightJoystick;

        finalPowerLeft = drivetrain.driveFunc((!checkDeadBand(finalPowerLeft, 1) ? Math.round(finalPowerLeft) : finalPowerLeft));
        finalPowerRight = drivetrain.driveFunc((!checkDeadBand(finalPowerRight, 1) ? Math.round(finalPowerRight) : finalPowerRight));
        System.out.println(finalPowerRight);

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
