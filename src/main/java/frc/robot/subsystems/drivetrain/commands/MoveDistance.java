package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.UnitModel;

import static frc.robot.Constants.CodeMonkey.MoveDistance.*;

public class MoveDistance extends CommandBase {
    private Drivetrain drivetrain;
    private PIDController pidController = new PIDController(kP, kI, kD);
    private double targetDistance;
    private UnitModel unitMan = new UnitModel(TICKS_PER_METER);

    public MoveDistance(Drivetrain drivetrain, double targetDistance) {
        this.drivetrain = drivetrain;
        this.targetDistance = targetDistance;
    }

    private double calcDistance(){
        return pidController.calculate(unitMan.toTicks(targetDistance));
    }

    @Override
    public void execute() {
        drivetrain.setPositionL(calcDistance());
        drivetrain.setPositionR(calcDistance());
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
