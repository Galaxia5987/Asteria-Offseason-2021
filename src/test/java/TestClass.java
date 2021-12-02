import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.Constants;
import frc.robot.PathUtil;
import org.junit.Test;

import java.util.List;


public class TestClass {
    private final RamseteController ramsete = new RamseteController(Constants.Autonomous.BETA, Constants.Autonomous.ZETA);
    private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Autonomous.KINEMATICS);
    private final SimpleMotorFeedforward leftFeedforward = new SimpleMotorFeedforward(Constants.Autonomous.Left_KS, Constants.Autonomous.Left_KV, Constants.Autonomous.Left_KA);
    private final SimpleMotorFeedforward rightFeedForward = new SimpleMotorFeedforward(Constants.Autonomous.Right_KS, Constants.Autonomous.Right_KV, Constants.Autonomous.Right_KA);

    @Test
    public void testFunction2() {
        List<Trajectory.State> pathStates = PathUtil.getTrajectory("paths/TakeCube1.wpilib.json").getStates();
        Trajectory.State goal;
        Trajectory.State currentState;
        ChassisSpeeds adjustedSpeeds;
        DifferentialDriveWheelSpeeds wheelSpeeds;
        double deltaAngle = 0;

        for (int i = 1; i < pathStates.size(); i++) {
            currentState = pathStates.get(i - 1);
            goal = pathStates.get(i);

            adjustedSpeeds = ramsete.calculate(currentState.poseMeters, goal);
            wheelSpeeds = kinematics.toWheelSpeeds(adjustedSpeeds);

            deltaAngle += 0.02 * 360 *
                    (wheelSpeeds.leftMetersPerSecond - wheelSpeeds.rightMetersPerSecond) / (1.7 * Math.PI);
        }

        currentState = pathStates.get(0);
        goal = pathStates.get(pathStates.size() - 1);

        System.out.println("The change in angle is: " + deltaAngle);
        System.out.println("The required change in angle is: " + Math.abs(currentState.poseMeters.getRotation().getDegrees() - goal.poseMeters.getRotation().getDegrees()));
//        System.out.println("Delta velocity wheels right: " + deltaVelocityR);
//        System.out.println("Delta velocity wheels left: " + deltaVelocityL);
//        double targetAngle = 195;
//        targetAngle = targetAngle < 0 ? targetAngle + 360 : targetAngle;
//        targetAngle = (targetAngle > 180 && targetAngle < 270) ? MAX_ANGLE : ((targetAngle > 270 && targetAngle < 360) ? MIN_ANGLE : targetAngle);
//        System.out.println("The corrected angle is: " + targetAngle);
    }
}
