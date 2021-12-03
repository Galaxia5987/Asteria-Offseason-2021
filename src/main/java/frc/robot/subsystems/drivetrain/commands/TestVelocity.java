package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.techfire225.webapp.FireLog;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class TestVelocity extends CommandBase {

    private Drivetrain drivetrain;
    private DoubleSupplier velocity;

    public TestVelocity(Drivetrain drivetrain, DoubleSupplier velocity) {
        this.drivetrain = drivetrain;
        this.velocity = velocity;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}


    @Override
    public void execute() {
        drivetrain.setVelocitiesAndFeedforward(velocity.getAsDouble(), velocity.getAsDouble(), 0, 0);
        FireLog.log("setpoint", velocity.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerL(0);
        drivetrain.setPowerR(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
