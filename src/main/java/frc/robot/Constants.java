/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpiutil.math.Nat;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static double LOOP_PERIOD = 0.2;
    public static double WHEEL_RADIUS = 0;
    public static class Kalman{
        public static double TICKS_PER_RADIAN = 2 * Math.PI / 4096;
        public static double MODEL_TOLERANCE = 0.8;
        public static double SENSOR_TOLERANCE = 0.2;
        public static double kA = 0.001;
        public static double kV = 0.023;
        public static double kP = 0.35;
        public static double kI = 0;
        public static double kD = 0.02;
        public static int SENSOR_POS = 0;
    }

}
