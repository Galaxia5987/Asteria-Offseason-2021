package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.PathUtil;
import frc.robot.autonomous.commands.FollowPath;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class Autonomous1 extends SequentialCommandGroup {
    private Drivetrain drivetrain;

    public Autonomous1(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addCommands(
                new FollowPath(PathUtil.getTrajectory("paths/PlaceInitcube.wpilib.json"), drivetrain),
                new FollowPath(PathUtil.getTrajectory("paths/TakeCube1.wpilib.json"), drivetrain),
                new FollowPath(PathUtil.getTrajectory("paths/PlaceScale1.wpilib.json"), drivetrain)
        );
    }
}
