package frc.robot.subsystems.flywheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Constants.Kalman.*;
import static frc.robot.Ports.KalmanFilter.*;

public class Flywheel {
    private TalonSRX motorMain = new TalonSRX(MAIN);
    private TalonSRX motorAux1 = new TalonSRX(AUX1);
    private TalonSRX motorAux2 = new TalonSRX(AUX2);
    private UnitModel unitModel = new UnitModel(TICKS_PER_RADIAN);

    public Flywheel() {
        motorMain.setSelectedSensorPosition(SENSOR_POS);
        motorMain.setSensorPhase(SENSOR_PHASE);
        motorMain.setInverted(MAIN_INVERTED);
        motorAux1.setInverted(AUX1_INVERTED);
        motorAux2.setInverted(AUX2_INVERTED);

        motorMain.config_kP(PID_X, kP);
        motorMain.config_kI(PID_X, kI);
        motorMain.config_kD(PID_X, kD);

        motorAux1.follow(motorMain);
        motorAux2.follow(motorMain);
    }

    public void setVoltage(double voltage) {
        motorMain.set(ControlMode.PercentOutput, voltage / 12);
    }

    public double getVelocity() {
        return unitModel.toVelocity(motorMain.getSelectedSensorVelocity());
    }

    public double calcVelocity(double distance) {
        distance = MathUtil.clamp(distance, 1.4, 11); //The camera can't really see beyond these distances, which means they are most likely erroneous.
        return MathUtil.clamp(.0755 * Math.pow(distance, 4) - 1.38254 * Math.pow(distance, 3) + 8.6493 * Math.pow(distance, 2) - 16.905 * distance + 71.998
                , 50, 120); //Prevent the shooter from speeding up too much, and from not activating.
    }

    public void terminate() {
        motorMain.set(ControlMode.PercentOutput, 0);
    }
}
