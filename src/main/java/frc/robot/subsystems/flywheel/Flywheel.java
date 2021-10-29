package frc.robot.subsystems.flywheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Constants.Kalman.*;
import static frc.robot.Ports.KalmanFilter.*;

public class Flywheel {
    private TalonSRX motor = new TalonSRX(FLY_WHEEL);
    private UnitModel unitModel = new UnitModel(TICKS_PER_METER);

    public Flywheel() {
        motor.setSelectedSensorPosition(SENSOR_POS);
        motor.setSensorPhase(SENSOR_PHASE);
        motor.setInverted(INVERTED);

        motor.config_kP(PID_X, kP);
        motor.config_kI(PID_X, kI);
        motor.config_kD(PID_X, kD);
    }

    public void setVelocity(double velocity){
        motor.set(ControlMode.Velocity, unitModel.toTicks100ms(velocity));
    }

    public double getVelocity(){
        return unitModel.toVelocity(motor.getSelectedSensorVelocity());
    }

    public double calcVelocity(double distance){
        distance = MathUtil.clamp(distance, 1.4, 11); //The camera can't really see beyond these distances, which means they are most likely erroneous.
        return MathUtil.clamp( .0755*Math.pow(distance, 4) - 1.38254*Math.pow(distance, 3) + 8.6493*Math.pow(distance, 2) - 16.905*distance + 71.998
                ,50,120); //Prevent the shooter from speeding up too much, and from not activating.
    }

    public void terminate(){
        motor.set(ControlMode.PercentOutput, 0);
    }
}
