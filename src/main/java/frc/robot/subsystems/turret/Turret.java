package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Constants.Turret.*;
import static frc.robot.Ports.Turret.*;

public class Turret extends SubsystemBase {
    private double currAngle = 90; // The current angle of the turret.
    private double targetAngle; // The angle the turret needs to get to.
    private UnitModel unitMan = new UnitModel(TICKS_PER_DEGREE); // The unit conversion module.
    private TalonSRX motor = new TalonSRX(motorPort); // The motor used to spin the turret.

    //Constructor.
    public Turret(){
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);
        motor.setSensorPhase(true);
    }

    /**
     * This function sets the angle the turret needs to be at.
     * @param targetAngle is the angle the turret needs to be at.
     */
    public void setTargetAngle(double targetAngle) {
        if(targetAngle > MAX_ANGLE)
            this.targetAngle = MAX_ANGLE;
        else if(targetAngle < MIN_ANGLE)
            this.targetAngle = MIN_ANGLE;
        else
            this.targetAngle = targetAngle;
    }

    /**
     * Gets the current angle of the turret
     * @return the current angle
     */
    public double getCurrAngle() {
        return currAngle;
    }

    /**
     * Gets the target angle of the turret
     * @return the target angle
     */
    public double getTargetAngle() {
        return targetAngle;
    }

    /**
     * Sets the current angle of the turret.
     * @param currAngle is the current angle of the turret.
     */
    public void setCurrAngle(double currAngle){
        this.currAngle = currAngle;
    }

    /**
     * Changes the position of the motor, hence changing the position of the turret.
     */
    public void setAngle(){
        motor.set(ControlMode.Position, unitMan.toTicks(targetAngle - currAngle));
    }

    /**
     * Checks whether or not the target angle and the current angle are equal in a certain error range.
     * @return whether the angles are equal.
     */
    public boolean anglesEqual(){
        if(currAngle == targetAngle + ERROR_RANGE || currAngle == targetAngle - ERROR_RANGE){
            return true;
        }
        return false;
    }

    /**
     * Sets the turret back to 0.
     */
    public void terminate(){
        targetAngle = 0;
        setAngle();
    }
}
