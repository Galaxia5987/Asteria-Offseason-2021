package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Constants.Turret.*;
import static frc.robot.Ports.Turret.*;

public class Turret extends SubsystemBase {
    private double currAngle = 90; // The current angle of the turret. [degrees]
    private double targetAngle; // The angle the turret needs to get to. [degrees]
    private UnitModel unitMan = new UnitModel(TICKS_PER_DEGREE); // The unit conversion module.
    private TalonSRX motor = new TalonSRX(MOTOR); // The motor used to spin the turret.

    //Constructor.
    public Turret() {
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);

        motor.setInverted(INVERTED);

        motor.setSensorPhase(SENSOR_PHASE);
    }

    /**
     * This function sets the angle the turret needs to be at. [degrees]
     *
     * @param targetAngle is the angle the turret needs to be at. [degrees]
     */
    public void setTargetAngle(double targetAngle) {
        targetAngle = targetAngle < 0 ? targetAngle + 360 : targetAngle;
        this.targetAngle = (targetAngle > 180 && targetAngle < 270) ? MAX_ANGLE : ((targetAngle > 270 && targetAngle < 360) ? MIN_ANGLE : targetAngle);
    }

    /**
     * Gets the current angle of the turret. [degrees]
     *
     * @return the current angle. [degrees]
     */
    public double getCurrAngle() {
        return currAngle;
    }

    /**
     * Gets the target angle of the turret. [degrees]
     *
     * @return the target angle. [degrees]
     */
    public double getTargetAngle() {
        return targetAngle;
    }

    /**
     * Sets the current angle of the turret. [degrees]
     *
     * @param currAngle is the current angle of the turret. [degrees]
     */
    public void setCurrAngle(double currAngle) {
        this.currAngle = currAngle;
    }

    /**
     * Changes the position of the motor, hence changing the position of the turret.
     */
    public void setPosition() {
        motor.set(ControlMode.Position, unitMan.toTicks(targetAngle - currAngle));
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public int getTicks() {
        return motor.getSelectedSensorPosition();
    }

    /**
     * Checks whether or not the target angle and the current angle are equal in a certain error range.
     *
     * @return whether the angles are equal.
     */
    public boolean anglesEqual() {
        if (currAngle <= targetAngle + ERROR_RANGE || currAngle >= targetAngle - ERROR_RANGE) {
            return true;
        }
        return false;
    }

    /**
     * Sets the turret back to 0.
     */
    public void terminate() {
        targetAngle = 0;
        setPosition();
    }
}
