package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.PathUtil;
import frc.robot.autonomous.commands.FollowPath;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class BottomPath extends SequentialCommandGroup {
    private final double power = 1;

    public BottomPath(Drivetrain drivetrain,
                      Shooter shooter,
                      Intake intake,
                      Conveyor conveyor,
                      Funnel funnel) {
        addCommands(
                new FollowPath(PathUtil.getTrajectory("paths/PlaceInitcube.wpilib.json"), drivetrain),
                new Shoot(conveyor, shooter, power).withTimeout(2),
                new FollowPath(PathUtil.getTrajectory("paths/TakeCube1.wpilib.json"), drivetrain),
                new PickUpBalls(conveyor, funnel, intake).withTimeout(2),
                new FollowPath(PathUtil.getTrajectory("paths/PlaceScale1.wpilib.json"), drivetrain),
                new Shoot(conveyor, shooter, power).withTimeout(2),
                new FollowPath(PathUtil.getTrajectory("paths/TakeCube2.wpilib.json"), drivetrain),
                new PickUpBalls(conveyor, funnel, intake).withTimeout(2),
                new FollowPath(PathUtil.getTrajectory("paths/PlaceScale2.wpilib.json"), drivetrain),
                new Shoot(conveyor, shooter, power).withTimeout(2)
        );
    }
}
