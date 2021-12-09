package frc.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commandgroups.PathMaker;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.auto.FollowPath;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

import java.util.ArrayList;

public class TestPath extends SequentialCommandGroup {
    String initPose;
    ArrayList<Double> errors = new ArrayList<>();
    Drivetrain drivetrain;
    Shooter shooter;
    Intake intake;
    Conveyor conveyor;
    Funnel funnel;
    Timer timer;
    Trajectory trajectory;
    PathMaker pathMaker;
    double average;

    public TestPath(String initPose,
                    Drivetrain drivetrain,
                    Shooter shooter,
                    Intake intake,
                    Conveyor conveyor,
                    Funnel funnel) {
        this.initPose = initPose;
        this.drivetrain = drivetrain;
        this.shooter = shooter;
        this.intake = intake;
        this.conveyor = conveyor;
        this.funnel = funnel;

        pathMaker = new PathMaker(drivetrain, shooter, intake, conveyor, funnel, initPose);
        addCommands(
                pathMaker
        );
    }

    @Override
    public void execute() {
        if(pathMaker.getCommand().getClass().equals(FollowPath.class)){
            trajectory = pathMaker.trajectory;

            if (trajectory.sample(timer.get()).timeSeconds == timer.get()) {
                double error = getError();
                errors.add(error);
                System.out.println("The error after " + timer.get() + " seconds is: " + error);

                average /= errors.size() / (double) (errors.size() - 1);
                average += error / (double) (errors.size() - 1);
                System.out.println("The average error up to " + timer.get() + " seconds is: " + average);
            }
        }
    }

    public double getError() {
        return Math.hypot(trajectory.sample(timer.get()).poseMeters.getX() - drivetrain.getPose().getX(),
                trajectory.sample(timer.get()).poseMeters.getY() - drivetrain.getPose().getY());
    }
}
