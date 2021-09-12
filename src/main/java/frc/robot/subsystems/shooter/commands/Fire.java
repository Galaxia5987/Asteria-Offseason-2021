package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;

public class Fire extends CommandBase {
    private Shooter sniper;
    private double distance;
    private double targetSpeed;

    public Fire(Shooter sniper, double distance) {
        this.sniper = sniper;
        this.distance = distance;
        addRequirements(sniper);
    }

    @Override
    public void initialize() {
        targetSpeed = getTargetSpeed(distance);
    }

    @Override
    public void execute() {
        sniper.setVelocity(targetSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        sniper.terminate();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public double getTargetSpeed(double distance){
        return 0;
    }
}
