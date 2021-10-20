/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static class Drivetrain {
        public static final double SHIFTER_COOLDOWN = 0.5; // Time after shifting the shifter is not to be used.
        public static final double TURNING_TOLERANCE = 1; // Stops the robot from shifting while the robot is turning.
        public static final double SHIFT_SPEED_TOLERANCE = 0.5; // Stops the robot from shifting while the robot is too fast
        public static final double JOYSTICK_DRIFT = 0.05;
    }

    public static class GTAdrive{
        public static double DEAD_BAND = 0.05;
    }

    public static class CodeMonkey{
        public static double WHEEL_RADIUS = 0; // [m]

        public static class TurnTo{
            public static double kP = 0.2;
            public static double kI = 0;
            public static double kD = 0.02;
            public static double TICKS_PER_METER = 2 * Math.PI * WHEEL_RADIUS / 4096;
            public static double ROBOT_RADIUS = 0;
        }

        public static class MoveDistance{
            public static double kP = 0.2;
            public static double kI = 0;
            public static double kD = 0.02;
            public static double TICKS_PER_METER = 2 * Math.PI * WHEEL_RADIUS / 4096;
        }
    }
}
