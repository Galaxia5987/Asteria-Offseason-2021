package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

import static frc.robot.Constants.GTAdrive.*;
import static frc.robot.Ports.MILF.JOYSTICK_LEFT;
import static frc.robot.Ports.MILF.JOYSTICK_RIGHT;

public class GTAdrive extends CommandBase {
    private Joystick joystickLeft = new Joystick(JOYSTICK_LEFT);
    private Joystick joystickRight = new Joystick(JOYSTICK_RIGHT);
    private Drivetrain drivetrain;
    private double powerRightJoystick = 0;
    private double powerLeftJoystick = 0;

    public GTAdrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
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

        powerRightJoystick *= 0.5;

        double finalPowerRight = powerLeftJoystick - powerRightJoystick;
        double finalPowerLeft = powerLeftJoystick + powerRightJoystick;

        finalPowerLeft = (!checkDeadBand(finalPowerLeft, 1)) ? Math.round(finalPowerLeft) : finalPowerLeft;
        finalPowerRight = (!checkDeadBand(finalPowerRight, 1)) ? Math.round(finalPowerRight) : finalPowerRight;

        if (powerRightJoystick == 0) {
            double difference = drivetrain.getVelocityTicksLeft() - drivetrain.getVelocityTicksRight();
            powerLeftJoystick -= difference * KP;
            powerRightJoystick += difference * KP;
            System.out.println(difference);
        }
        double averageSpeed = (Math.abs(drivetrain.getVelocityTicksLeft()) + Math.abs(drivetrain.getVelocityTicksRight())) / 2;
        powerLeftJoystick = powerLeftJoystick * averageSpeed * kFilter;
        powerRightJoystick = powerRightJoystick * averageSpeed * kFilter;

//        if(powerRightJoystick < 0)
//            powerLeftJoystick *= -1;

        drivetrain.setPowerR(finalPowerRight * SPEED_MULTIPLIER);
        drivetrain.setPowerL(finalPowerLeft * SPEED_MULTIPLIER);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
