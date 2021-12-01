package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.Robot;
import frc.robot.UnitModel;

public class Drivetrain extends SubsystemBase {
    private TalonFX frMotor = new TalonFX(Ports.Drivetrain.FR);
    private TalonFX rrMotor = new TalonFX(Ports.Drivetrain.RR);
    private TalonFX flMotor = new TalonFX(Ports.Drivetrain.FL);
    private TalonFX rlMotor = new TalonFX(Ports.Drivetrain.RL);
    private Solenoid piston = new Solenoid(0);
    private UnitModel highGear = new UnitModel(Constants.Drivetrain.HIGH_TICKS_PER_METER);
    private UnitModel lowGear = new UnitModel(Constants.Drivetrain.LOW_TICKS_PER_METER);
    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(Math.toRadians(-Robot.navx.getAngle())),
            new Pose2d(5.0, 13.5, new Rotation2d()));


    public Timer timer = new Timer();

    public Drivetrain() {
        rrMotor.setInverted(Ports.Drivetrain.REVERSER_RR);
        frMotor.setInverted(Ports.Drivetrain.REVERSER_FR);
        flMotor.setInverted(Ports.Drivetrain.REVERSER_FL);
        rlMotor.setInverted(Ports.Drivetrain.REVERSER_RL);

        rrMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SR1);
        frMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SR2);
        flMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SF1);
        rlMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SF2);

        frMotor.config_kP(0, Constants.Drivetrain.kPRight);
        frMotor.config_kI(0, Constants.Drivetrain.kIRight);
        frMotor.config_kD(0, Constants.Drivetrain.kDRight);
        flMotor.config_kP(0, Constants.Drivetrain.kPLeft);
        flMotor.config_kI(0, Constants.Drivetrain.kILeft);
        flMotor.config_kD(0, Constants.Drivetrain.kDLeft);


        rlMotor.follow(flMotor);
        rrMotor.follow(frMotor);


        starTimer();
    }

    /**
     * @param valueMotorR for set power function + the Right motor is powered in percent.
     */
    public void setPowerR(double valueMotorR) {
        frMotor.set(ControlMode.PercentOutput, valueMotorR);
    }

    /**
     * @param valueMotorL for power function + the left motor is powered by percents.
     */
    public void setPowerL(double valueMotorL) {
        flMotor.set(ControlMode.PercentOutput, valueMotorL);
    }

    /**
     * @return the velocity of right motor. [m/s]
     */
    public double getVelocityRight() {
        return getUnitModel().toVelocity(frMotor.getSelectedSensorVelocity());
    }

    /**
     * @return the velocity of left motor. [m/s]
     */

    public double getVelocityLeft() {
        return getUnitModel().toVelocity(frMotor.getSelectedSensorVelocity());
    }

    /**
     * @return if the solenoid is on high gear.
     */
    public UnitModel getUnitModel() {
        if (isHighGear()) {
            return highGear;
        }
        return lowGear;
    }

    /**
     * @param mode refers to which gear the selenoid should move (high for high gear and low gear).
     */
    public void shiftGear(GearMode mode) {
        switch (mode) {
            case HIGH:
                if (canShiftLow()) {
                    shiftLow();
                }
                break;
            case LOW:
                if (canShiftHigh()) {
                    shiftHigh();
                }
                break;
        }
    }

    /**
     * @return the piston position them, changes the gear.
     */
    public boolean isHighGear() {
        return piston.get();
    }

    public void shiftHigh() {
        starTimer();
        piston.set(true);
    }

    public void shiftLow() {
        starTimer();
        piston.set(false);
    }

    /**
     * the opposite of what the piston position actually is.
     */
    public void toggle() {
        piston.set(!piston.get());
    }

    /**
     * @return if the selenoid can shift to low gear
     */
    public boolean canShiftLow() {
        return getVelocityLeft() < Constants.Drivetrain.SHIFT_SPEED_TOLERANCE &&
                getVelocityRight() < Constants.Drivetrain.SHIFT_SPEED_TOLERANCE &&
                isHighGear() &&
                timer.hasElapsed(Constants.Drivetrain.SHIFTER_COOLDOWN);

    }

    /**
     * @return if the selenoid can shift to high gear
     */
    public boolean canShiftHigh() {
        return !isHighGear() && timer.hasElapsed(Constants.Drivetrain.SHIFTER_COOLDOWN);
    }

    /**
     * starts the timer
     */
    public void starTimer() {
        timer.reset();
        timer.start();
    }


    /**
     * which gearmodes exist
     */
    public enum GearMode {
        HIGH, LOW
    }

    public double smoothingFunction(double x) {
        if (x < 0) {
            return (-(1 - Math.sqrt(1 - Math.pow(-x, 2))));
        }
        return (1 - Math.sqrt(1 - Math.pow(x, 2)));
    }

    public void setVelocityRight(double velocityRight) {
        frMotor.set(ControlMode.Velocity, getUnitModel().toTicks100ms(velocityRight));
    }

    public void setVelocityLeft(double velocityLeft) {
        flMotor.set(ControlMode.Velocity, getUnitModel().toTicks100ms(velocityLeft));
    }

    public double getDistanceLeft() {
        return flMotor.getSensorCollection().getIntegratedSensorPosition();
    }

    public double getDistanceRight() {
        return frMotor.getSensorCollection().getIntegratedSensorPosition();
    }

    /**
     * @return position in meters
     */
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    /**
     * @param pose will reset position to this pose
     */
    public void resetPose(Pose2d pose) {
        odometry.resetPosition(pose, new Rotation2d(Math.toRadians(-Robot.navx.getAngle())));
    }

    @Override
    /**
     * updates position and rotation over time
     */
    public void periodic() {
        odometry.update(new Rotation2d(Math.toRadians(-Robot.navx.getAngle())), getDistanceLeft(), getDistanceRight());
    }
}

