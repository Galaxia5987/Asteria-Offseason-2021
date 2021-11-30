package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class FollowPath extends CommandBase {
    private Trajectory trajectory;
    private Drivetrain drivetrain;

    public FollowPath(Trajectory trajectory, Drivetrain drivetrain) {
        this.trajectory = trajectory;
        this.drivetrain = drivetrain;
    }
}
