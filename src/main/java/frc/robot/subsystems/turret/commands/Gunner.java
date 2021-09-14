package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;

public class Gunner extends CommandBase {
    private double targetAngle; // Angle the turret needs to be at.
    private Turret gunnerMan = new Turret(); // Turret class object (in order to move the motor).

    // Constructor.
    public Gunner(double targetAngle){
        this.targetAngle = targetAngle;
    }

    // Executes all the commands in a loop.
    @Override
    public void execute() {
        gunnerMan.setTargetAngle(targetAngle);
        gunnerMan.setAngle();
        gunnerMan.setCurrAngle(targetAngle);
    }

    // Resets the turret.
    @Override
    public void end(boolean interrupted) {
        gunnerMan.terminate();
    }

    // Continues running the program until interrupted.
    @Override
    public boolean isFinished() {
        return false;
    }
}
