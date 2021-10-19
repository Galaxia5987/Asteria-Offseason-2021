package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;

import static frc.robot.RobotContainer.xboxController;

public class GTAdrivebetter extends CommandBase {

    private Drivetrain drivetrain;

    public class DrivetrainDefaultCommand extends CommandBase {
        private final XboxController xboxController = RobotContainer.xboxController;

        public DrivetrainDefaultCommand(Drivetrain drivetrain) {
        }
    }
    @Override
    public void initialize() {

        super.initialize();
    }

    @Override
    public void execute() {

        super.execute();
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
