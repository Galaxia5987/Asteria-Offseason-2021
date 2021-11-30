package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

import java.io.IOException;
import java.nio.file.Path;

public class PathUtil {

    public static Trajectory getTrajectory(String trajectoryJson) {
        Trajectory trajectory = null;

        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(
                    "paths/TakeCube1.wpilib.json"
            );
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: paths/TakeCube1.wpilib.json", ex.getStackTrace());
        }
        return trajectory;
    }
}
