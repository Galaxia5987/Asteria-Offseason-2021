package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;

public class TurnToTarget extends CommandBase {
    private final double init_yaw;
    private double turret_yaw;
    private final Turret gunner;

    public TurnToTarget(double init_yaw, Turret gunner) {
        this.init_yaw = init_yaw;
        this.gunner = gunner;
    }

    @Override
    public void initialize() {
        turret_yaw = gunner.getCurrAngle();
    }

    @Override
    public void execute() {
        double target_yaw = turret_yaw - init_yaw;
        gunner.setCurrAngle(target_yaw);
        gunner.setPosition();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return gunner.anglesEqual();
    }
}
