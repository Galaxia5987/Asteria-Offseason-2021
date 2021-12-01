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
    public static final class Autonomous {

        public static final double MAX_SPEED = 0.0;
        public static final double MAX_ACCELERATION = 0.0;
        public static final double MAX_CENTRIPETAL_ACCELERATION = 0.0;
        public static final double B_VALUE = 2;
        public static final double ZETA_VALUE = 0.7;
        public static final double KINEMATICS = 1.7;
        public static final double Left_KA = 0;
        public static final double Left_KS = 0;
        public static final double Left_KV = 0;
        public static final double Right_KA = 0;
        public static final double Right_KS = 0;
        public static final double Right_KV = 0;

    }

    public static final class Intake {
        public static final double POWER = 0.5; // [%]
    }

    public static final class Conveyor {
        public static final int SENSE_DISTANCE = 0;
        public static final int MIN_PROXIMITY_DISTANCE = 0;
        public static final int MAX_PROXIMITY_DISTANCE = 0;
        public static final int MAX_BALLS = 5;
        public static final boolean NEW_SHOOTER_BALL = false;
        public static final boolean NEW_FUNNEL_BALL = false;
        public static final int REVERSE_MOTOR_POWER = -1;
        public static int BALL_NUMBER = 3;
        public static double POWER = 0.7;
    }

    public static class Funnel {
        public static double POWER = 0.5;
    }

    public static class Shooter {
        public static final double DISTANCE = 5.25;
        public static final double SPEED = 35.668789;
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 1.5;
        public static final double ANGLE = 30; // 30 degrees
        public static final double INIT_HEIGHT = 0;
        public static final double WHEEL_RADIUS = 0;
        public static final double TICKS = 4096;

    }

    public static class Turret {
        public static final double ERROR_RANGE = 2; // degrees
        public static final double MAX_TICKS = 912; // degrees
        public static final double MIN_TICKS = -1165; // degrees
        public static final double TICKS_PER_DEGREE = 4096.0 / 360.0;
        public static final double kP = 0.2;
        //        public static final double kP = 3.5;
        public static final double kI = 0;
        //        public static final double kI = 0.01;
        public static final double kD = 0.04;
        //        public static final double kD = 150;
        public static final double DEADBAND = 0.1;
        public static final double MIN_JOYSTICK_DISTANCE = 0.6;
    }

    public static class Drivetrain {
        public static final double SHIFTER_COOLDOWN = 0.5; // Time after shifting the shifter is not to be used.
        public static final double TURNING_TOLERANCE = 1; // Stops the robot from shifting while the robot is turning.
        public static final double SHIFT_SPEED_TOLERANCE = 0.5; // Stops the robot from shifting while the robot is too fast
        public static final double JOYSTICK_DRIFT = 0.05;
    }
}
