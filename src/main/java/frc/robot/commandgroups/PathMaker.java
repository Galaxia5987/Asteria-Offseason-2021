package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.PathUtil;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.auto.FollowPath;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.funnel.commandush.PowerWheels;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.commands.Commandy;
import frc.robot.subsystems.shooter.Shooter;

public class PathMaker extends SequentialCommandGroup {
    private final double m_shotPower = 1;
    private final double m_intakePower = 0.4;

    public PathMaker(Drivetrain drivetrain,
                     Shooter shooter,
                     Intake intake,
                     Conveyor conveyor,
                     Funnel funnel,
                     String initPose) {
        String[] paths = getPaths(initPose);
        Trajectory trajectory;

        addCommands(
                new FollowPath(drivetrain,
                        PathUtil.getTrajectory(paths[0]),
                        PathUtil.getTrajectory(paths[0]).getInitialPose())
        );

        for (int i = 1; i < paths.length; i++) {
            if (paths[i].contains("paths")) {
                trajectory = PathUtil.getTrajectory(paths[i]);
                addCommands(
                        new FollowPath(
                                drivetrain,
                                trajectory,
                                trajectory.getStates().get(trajectory.getStates().size() - 1).poseMeters
                        )
                );
            } else if (paths[i].contains("shoot")) {
                if (paths[i].contains("intake"))
                    addCommands(
                            new ParallelCommandGroup(
                                    new Shoot(conveyor, shooter, m_shotPower),
                                    new Commandy(intake, m_intakePower),
                                    new PowerWheels(funnel, m_intakePower)
                            ).withTimeout(2)
                    );
                else
                    addCommands(
                            new Shoot(conveyor, shooter, m_shotPower).withTimeout(2)
                    );
            } else {
                addCommands(
                        new ParallelCommandGroup(
                                new Commandy(intake, m_intakePower),
                                new PowerWheels(funnel, m_intakePower)
                        ).withTimeout(2)
                );
            }
        }
    }

    public String[] getPaths(String initPose) {
        switch (initPose) {
            case "top":
                return new String[]{
                        "paths/output/InitPathTop.wpilib.json",
                        "shootintake",
                        "paths/output/PlaceScale1Top.wpilib.json",
                        "shoot",
                        "paths/output/TakeCube1Top.wpilib.json",
                        "intake",
                        "paths/output/PlaceScale2Top.wpilib.json",
                        "shoot"
                };
            case "bottom":
                return new String[]{
                        "paths/output/InitPathBottom.wpilib.json",
                        "shootintake",
                        "paths/output/PlaceScale1Bottom.wpilib.json",
                        "shoot",
                        "paths/output/TakeCube1Bottom.wpilib.json",
                        "intake",
                        "paths/output/PlaceScale2Bottom.wpilib.json",
                        "shoot"
                };
            default:
                return null;
        }
    }
}
