package frc.robot.subsystems.drivetrain.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class FollowPath extends CommandBase {
    private final Drivetrain drivetrain;
    private final Trajectory trajectory;
    private final RamseteController ramsete = new RamseteController(Constants.Autonomous.B_VALUE, Constants.Autonomous.ZETA_VALUE);
    private final Timer timer = new Timer();
    private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Autonomous.KINEMATICS);
    private final SimpleMotorFeedforward leftFeedforward = new SimpleMotorFeedforward(Constants.Autonomous.Left_KS, Constants.Autonomous.Left_KV, Constants.Autonomous.Left_KA);
    private final SimpleMotorFeedforward rightFeedForward = new SimpleMotorFeedforward(Constants.Autonomous.Right_KS, Constants.Autonomous.Right_KV, Constants.Autonomous.Right_KA);
    private double currentVelocityL;
    private double currentVelocityR;
    private double currentTime;

    public FollowPath(Drivetrain drivetrain, Trajectory trajectory) {
        this.drivetrain = drivetrain;
        this.trajectory = trajectory;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetPose(trajectory.sample(0).poseMeters);
        timer.reset();
        timer.start();

        currentVelocityL = drivetrain.getVelocityLeft();
        currentVelocityR = drivetrain.getVelocityRight();

        currentTime = timer.get();
    }

    @Override
    public void execute() {
        Trajectory.State goal = trajectory.sample(timer.get());
        ChassisSpeeds adjustedSpeeds = ramsete.calculate(drivetrain.getPose(), goal);
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(adjustedSpeeds);
        double left = wheelSpeeds.leftMetersPerSecond;
        double right = wheelSpeeds.rightMetersPerSecond;
        double deltaVelocityL = drivetrain.getVelocityLeft() - currentVelocityL;
        double deltaVelocityR = drivetrain.getVelocityRight() - currentVelocityR;
        double deltaTime = timer.get() - currentTime;

        double accelerationR = deltaVelocityR / deltaTime;
        double accelerationL = deltaVelocityL / deltaTime;

        double ffR = rightFeedForward.calculate(drivetrain.getVelocityRight());
        double ffL = leftFeedforward.calculate(drivetrain.getVelocityLeft());

        drivetrain.setVelocitiesAndFeedforward(left, right, ffL, ffR);
        currentVelocityL = drivetrain.getVelocityLeft();
        currentVelocityR = drivetrain.getVelocityRight();
        currentTime = timer.get();


    }

    @Override
    public boolean isFinished() {
        return timer.hasElapsed(trajectory.getTotalTimeSeconds());
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
    }
}
