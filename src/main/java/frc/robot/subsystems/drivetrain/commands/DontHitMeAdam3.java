package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class DontHitMeAdam3 extends CommandBase {
    private final XboxController xboxController = RobotContainer.xboxControllerDriver;
    private final Drivetrain drivetrain;

    public DontHitMeAdam3(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        double rt = xboxController.getTriggerAxis(GenericHID.Hand.kRight);
        double lt = xboxController.getTriggerAxis(GenericHID.Hand.kLeft);
        double x = xboxController.getX(GenericHID.Hand.kRight);
        double x2 = xboxController.getX(GenericHID.Hand.kLeft);

        if (Math.abs(x) < 0.1) x = 0;
        if (Math.abs(x2) < 0.1) x2 = 0;
        if (Math.abs(rt) < 0.1) rt = 0;
        if (Math.abs(lt) < 0.1) lt = 0;

        x *= 0.5;
        x2 *= 0.5;
//        x = drivetrain.calmEasingFunction(x);
//        y = drivetrain.calmEasingFunction(y);

//        drivetrain.setVelocity((y - x) * SPEED_MULTIPLIER, true);
//        drivetrain.setVelocity((y + x) * SPEED_MULTIPLIER, false);

        drivetrain.setPowerR((rt - lt) - (x + x2));
        drivetrain.setPowerL((rt - lt) + (x + x2));
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
