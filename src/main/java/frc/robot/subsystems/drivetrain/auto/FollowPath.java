package frc.robot.subsystems.drivetrain.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.autonomous.Path;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class FollowPath extends CommandBase {
    private final Drivetrain drivetrain;
    private final Trajectory trajectory;

    private final RamseteController ramsete = new RamseteController(Constants.Autonomous.B_VALUE, Constants.Autonomous.ZETA_VALUE);

    private final Timer timer = new Timer();

    public FollowPath(Drivetrain drivetrain, Trajectory trajectory){
        this.drivetrain = drivetrain;
        this.trajectory = trajectory;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.setPose(trajectory.sample(0).poseMeters);
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(trajectory.getTotalTimeSeconds());

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
