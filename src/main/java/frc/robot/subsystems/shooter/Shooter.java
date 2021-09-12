package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Ports.Shooter.*;
import static frc.robot.Constants.Shooter.*;

public class Shooter extends SubsystemBase {
    public static double targetDistance;
    private final TalonSRX motorMain = new TalonSRX(portMain);
    private final TalonSRX motorAux1 = new TalonSRX(portAux1);
    private final TalonSRX motorAux2 = new TalonSRX(portAux2);
    private final UnitModel unitMan = new UnitModel(TICKS);

    public Shooter() {
        motorMain.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        motorAux1.follow(motorMain);
        motorAux2.follow(motorMain);
        motorMain.config_kP(0, kP);
        motorMain.config_kI(0, kI);
        motorMain.config_kD(0, kD);
    }

    public double getVelocity(){
        double ticks100ms = motorMain.getSelectedSensorVelocity();
        return unitMan.toVelocity(ticks100ms);
    }

    public void setVelocity(double velocity){
        motorMain.set(ControlMode.Velocity, unitMan.toTicks100ms(velocity));
    }

    public void terminate(){
        motorMain.set(ControlMode.PercentOutput, 0);
    }
}
