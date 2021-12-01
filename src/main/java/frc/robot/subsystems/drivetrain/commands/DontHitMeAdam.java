package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;


public class DontHitMeAdam extends CommandBase {
    XboxController xboxController = RobotContainer.xboxControllerDriver;
    Drivetrain drivetrain;

    public DontHitMeAdam(Drivetrain drivetrain) {
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        double y = -xboxController.getY(GenericHID.Hand.kLeft);
        double x = xboxController.getX(GenericHID.Hand.kLeft);

        if (Math.abs(x) < 0.1) x = 0;
        if (Math.abs(y) < 0.1) y = 0;

        x *= 0.5;
//        x = drivetrain.calmEasingFunction(x);
//        y = drivetrain.calmEasingFunction(y);

//        drivetrain.setVelocity((y - x) * SPEED_MULTIPLIER, true);
//        drivetrain.setVelocity((y + x) * SPEED_MULTIPLIER, false);

        drivetrain.setPowerR(y - x);
        drivetrain.setPowerL(y + x);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}

