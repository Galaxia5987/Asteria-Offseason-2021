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

    public static class Shooter {
        public static final double DISTANCE = 5.25;
        public static final double SPEED = 35.668789;
        public static final double kP = 0;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double ANGLE = 30; // 30 degrees
        public static final double INIT_HEIGHT = 0;
        public static final double WHEEL_RADIUS = 0;
        public static final double TICKS = 4096;
    }
}
