package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.UnitModel;

import static frc.robot.Constants.Drivetrain.*;

public class Drivetrain extends SubsystemBase {
    private TalonFX frMotor = new TalonFX(Ports.Drivetrain.FR);
    private TalonFX rrMotor = new TalonFX(Ports.Drivetrain.RR);
    private TalonFX flMotor = new TalonFX(Ports.Drivetrain.FL);
    private TalonFX rlMotor = new TalonFX(Ports.Drivetrain.RL);
    private Solenoid piston = new Solenoid(0);
    private UnitModel highGear = new UnitModel(HIGH_TICKS_PER_METER);
    private UnitModel lowGear = new UnitModel(LOW_TICKS_PER_METER);


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

        rlMotor.follow(flMotor);
        rrMotor.follow(frMotor);

        configPID();

//        rrMotor.setNeutralMode(NeutralMode.Brake);
//        frMotor.setNeutralMode(NeutralMode.Brake);
//        flMotor.setNeutralMode(NeutralMode.Brake);
//        rlMotor.setNeutralMode(NeutralMode.Brake);

        starTimer();
    }

    public void configPID(){
        frMotor.config_kP(0, kPRight);
        frMotor.config_kI(0, kIRight);
        frMotor.config_kD(0, kDRight);
        frMotor.config_kF(0, kFRight);

        flMotor.config_kP(0, kPLeft);
        flMotor.config_kI(0, kILeft);
        flMotor.config_kD(0, kDLeft);
        flMotor.config_kF(0, kFLeft);
    }

    /**
     * Function that sets the velocity of the motors on the right side of the robot.
     *
     * @param velocityRight is the velocity of the motors. [m/s]
     * @param feedforward   is the arbitrary feed forward given to the motors. [V]
     */
    public void setVelocityRight(double velocityRight, double feedforward) {
        frMotor.set(ControlMode.Velocity, getUnitModel().toTicks100ms(velocityRight), DemandType.ArbitraryFeedForward, feedforward / 12);
    }

    /**
     * Function that sets the velocity of the motors on the left side of the robot.
     *
     * @param velocityLeft is the velocity of the motors. [m/s]
     * @param feedforward  is the arbitrary feed forward given to the motors. [V]
     */
    public void setVelocityLeft(double velocityLeft, double feedforward) {
        flMotor.set(ControlMode.Velocity, getUnitModel().toTicks100ms(velocityLeft), DemandType.ArbitraryFeedForward, feedforward / 12);
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

    public double driveFunc(double x) {
        return 3 * Math.asin(Math.pow(Math.abs(x), 1.5)) * Math.signum(x) * 0.65;
    }


    @Override
    public void periodic() {

    }
}

