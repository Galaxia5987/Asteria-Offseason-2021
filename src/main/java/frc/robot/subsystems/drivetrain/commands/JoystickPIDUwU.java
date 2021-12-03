package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.techfire225.webapp.FireLog;

public class JoystickPIDUwU extends CommandBase {
    private final Drivetrain drivetrain;


    public JoystickPIDUwU(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double velocity = -RobotContainer.xboxControllerOperator.getY(GenericHID.Hand.kRight) * Constants.Drivetrain.VELOCITY;
        FireLog.log("velocity", velocity);
        FireLog.log("Velocity Left", drivetrain.getVelocityLeft());
        FireLog.log("Velocity right", drivetrain.getVelocityRight());
        drivetrain.setVelocityRight(velocity, velocity);
        drivetrain.setVelocityLeft(velocity, velocity);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setPowerR(0);
        drivetrain.setPowerL(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
