package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Ports.Shooter.*;
import static frc.robot.Constants.Shooter.*;

/**
 * This is the class used to do all necessary calculations
 * for the shooter
 * <p>
 * motorMain - the leading motor, all calculations are done
 * based on this motor
 * <p>
 * motorAux1/2 - these two motors follow motorMain
 */
public class Shooter extends SubsystemBase {
    private final TalonSRX motorMain = new TalonSRX(portMain);
    private final VictorSPX motorAux1 = new VictorSPX(portAux1);
    private final VictorSPX motorAux2 = new VictorSPX(portAux2);
    public static UnitModel unitMan = new UnitModel(TICKS);

    /**
     * Here the constants for the PID is set for the TalonSRX
     * computer, and the aux motors are set to follow
     */
    public Shooter() {
        motorMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
//        motorAux1.follow(motorMain);
//        motorAux2.follow(motorMain);
        motorMain.config_kP(0, kP);
        motorMain.config_kI(0, kI);
        motorMain.config_kD(0, kD);

        motorMain.setInverted(MAIN_INVERTED);
        motorAux1.setInverted(AUX1_INVERTED);
        motorAux2.setInverted(AUX2_INVERTED);
    }


    /**
     * This function gets the velocity from the TalonSRX,
     * and converts it to the required units
     *
     * @return the velocity in rps
     */
    public double getVelocity() {
        double ticks100ms = motorMain.getSelectedSensorVelocity();
        return unitMan.toVelocity(ticks100ms);
    }

    /**
     * This function simply sets the speed for the motor to get to
     *
     * @param velocity is the target velocity for the motors
     */
    public void setVelocity(double velocity) {
        motorMain.set(ControlMode.Velocity, unitMan.toTicks100ms(velocity));
    }

    public void setPower(double power) {
        motorMain.set(ControlMode.PercentOutput, power);
    }

    /**
     * This program stops the motors
     */
    public void terminate() {
        motorMain.set(ControlMode.PercentOutput, 0);
    }
}
