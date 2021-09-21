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

    public static class Turret {
        public static final double ERROR_RANGE = 2; // degrees
        public static final double MAX_TICKS = 2048; // degrees
        public static final double MIN_TICKS = 0; // degrees
        public static final double TICKS_PER_DEGREE = 4096.0 / 360.0;
        public static final double kP = 0.4;
        //        public static final double kP = 3.5;
        public static final double kI = 0;
        //        public static final double kI = 0.01;
        public static final double kD = 0.04;
//        public static final double kD = 150;
        public static final double DEADBAND = 0.1
        ;
    }
}
