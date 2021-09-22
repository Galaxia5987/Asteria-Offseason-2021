package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class Gas extends CommandBase {
    private final Drivetrain drivetrain;
    private final XboxController xboxController = RobotContainer.xboxControllerOperator;

    public Gas(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.setPowerL(
//                deadband(-xboxController.getY(GenericHID.Hand.kLeft)) + deadband(xboxController.getX(GenericHID.Hand.kRight)) * 0.8
                drivetrain.yourmama(deadband(-xboxController.getY(GenericHID.Hand.kLeft))) + deadband(xboxController.getX(GenericHID.Hand.kRight)) * 0.8

//                deadband(-xboxController.getY(GenericHID.Hand.kLeft))
        );
        drivetrain.setPowerR(
//                deadband(-xboxController.getY(GenericHID.Hand.kLeft)) - deadband(xboxController.getX(GenericHID.Hand.kRight)) * 0.8
                drivetrain.yourmama(deadband(-xboxController.getY(GenericHID.Hand.kLeft))) - deadband(xboxController.getX(GenericHID.Hand.kRight)) * 0.8
//                deadband(-xboxController.getY(GenericHID.Hand.kRight))
        );
    }

    private double deadband(double drift) {
        if (Math.abs(drift) < Constants.Drivetrain.JOYSTICK_DRIFT)
            return 0;
        return drift;
    }
}

