package frc.robot.subsystems.drivetrain.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
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
    private final RamseteController ramsete = new RamseteController(Constants.Autonomous.BETA, Constants.Autonomous.ZETA);
    private final Timer timer = new Timer();
    private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Autonomous.TRACK_WIDTH);
    private final SimpleMotorFeedforward leftFeedforward = new SimpleMotorFeedforward(Constants.Autonomous.KS, Constants.Autonomous.KV, Constants.Autonomous.KA);
    private final SimpleMotorFeedforward rightFeedForward = new SimpleMotorFeedforward(Constants.Autonomous.KS, Constants.Autonomous.KV, Constants.Autonomous.KA);
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
//        drivetrain.resetPose(trajectory.sample(0).poseMeters);
        drivetrain.resetPose(trajectory.getInitialPose());
        timer.reset();
        timer.start();

        currentVelocityL = drivetrain.getVelocityLeft();
        currentVelocityR = drivetrain.getVelocityRight();

        currentTime = timer.get();
    }

    @Override
    public void execute() {
        System.out.println(timer.get());
        Trajectory.State goal = trajectory.sample(timer.get());
        System.out.println("goal: " + goal.poseMeters.getX() + ", " + goal.poseMeters.getY());
        ChassisSpeeds adjustedSpeeds = ramsete.calculate(drivetrain.getPose(), goal);
        System.out.println("adjusted speed: " + adjustedSpeeds.vxMetersPerSecond);
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(adjustedSpeeds);
        double left = wheelSpeeds.leftMetersPerSecond;
        double right = wheelSpeeds.rightMetersPerSecond;
        System.out.println("left input " + left);
        System.out.println("right input " + right);
        double deltaVelocityL = drivetrain.getVelocityLeft() - currentVelocityL;
        double deltaVelocityR = drivetrain.getVelocityRight() - currentVelocityR;
        double deltaTime = timer.get() - currentTime;

        double accelerationR = deltaVelocityR / deltaTime;
        double accelerationL = deltaVelocityL / deltaTime;

        double ffR = rightFeedForward.calculate(right, accelerationR);
        double ffL = leftFeedforward.calculate(left, accelerationL);

        drivetrain.setVelocitiesAndFeedforward(left, right, 0, 0);
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
        drivetrain.terminate();
    }
}
