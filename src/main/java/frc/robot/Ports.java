package frc.robot;

/**
 * Data for- motor power, is motor inverted, solenoid, funnel proximity, shooter proximity.
 */
public class Ports {
    public static class Conveyor {
        public static final int MOTOR = 21;
        public static final boolean IS_MOTOR_INVERTED = false;
        public static final int SOLENOID = 2;
        public static final int FUNNEL_PROXIMITY = 0;
        public static int SHOOTER_PROXIMITY = 0;
    }

    public static class Intake {
        public static final int MOTOR = 20;
        public static final int SOLENOID = 1;
        public static final boolean IS_MOTOR_INVERTED = false;
    }

    public static class Funnel {
        /**
         * input motor data.
         * input boolean motor data.
         */
        public static final int MOTOR = 19;
        public static final boolean IS_MOTOR_INVERTED = true;
    }
}