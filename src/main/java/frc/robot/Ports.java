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

    public static class Shooter {
        public static final int portMain = 23;
        public static final int portAux1 = 24;
        public static final int portAux2 = 25;
        public static final boolean MAIN_INVERTED = false;
        public static final boolean AUX1_INVERTED = true;
        public static final boolean AUX2_INVERTED = true;
    }

    public static class Turret {
        public final static boolean INVERTED = false;
        public final static boolean SENSOR_PHASE = true;
        public final static int MOTOR = 22;
    }

    public static class Drivetrain {
        public static int FR = 12;
        public static int RR = 13;
        public static int FL = 10;
        public static int RL = 11;

        public static boolean REVERSER_FR = true;
        public static boolean REVERSER_RR = true;
        public static boolean REVERSER_FL = false;
        public static boolean REVERSER_RL = false;

        public static boolean REVERSER_SR1 = false;
        public static boolean REVERSER_SR2 = false;
        public static boolean REVERSER_SF1 = false;
        public static boolean REVERSER_SF2 = false;
    }
}