package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.UnitModel;

import static frc.robot.Constants.CodeMonkey.TurnTo.*;

public class TurnTo extends CommandBase {
    private double currAngle;
    private double requiredAngle;
    private Drivetrain drivetrain;
    private PIDController pidController = new PIDController(kP, kI, kD);
    private UnitModel unitMan = new UnitModel(TICKS_PER_METER);

    public TurnTo(double requiredAngle, Drivetrain drivetrain, double currAngle) {
        this.requiredAngle = requiredAngle;
        this.drivetrain = drivetrain;
        this.currAngle = currAngle;
    }

    public double calcDistance(){
        double radians = Math.PI * (requiredAngle - currAngle) / 180;
        return pidController.calculate(unitMan.toTicks(radians * ROBOT_RADIUS));
    }

    @Override
    public void execute() {
        drivetrain.setPositionL(calcDistance());
        drivetrain.setPositionR(-calcDistance());
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
