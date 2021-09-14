package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;

public class Gunner extends CommandBase {
    private double targetAngle;
    private Turret gunnerMan = new Turret();

    public Gunner(double targetAngle){
        this.targetAngle = targetAngle;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        gunnerMan.setTargetAngle(targetAngle);
        gunnerMan.setAngle();
        gunnerMan.setCurrAngle(targetAngle);
    }

    @Override
    public void end(boolean interrupted) {
        gunnerMan.terminate();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
