package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.UnitModel;

import static frc.robot.Constants.Turret.*;
import static frc.robot.Ports.Turret.*;

public class Turret {
    private double currAngle = 0;
    private double targetAngle;
    private UnitModel unitMan = new UnitModel(TICKS_PER_DEGREE);
    private TalonSRX motor = new TalonSRX(motorPort);

    public Turret(){
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);
        motor.setSensorPhase(true);
    }

    public void setTargetAngle(double targetAngle) {
        if(targetAngle > MAX_ANGLE)
            this.targetAngle = MAX_ANGLE;
        else if(targetAngle < MIN_ANGLE)
            this.targetAngle = MIN_ANGLE;
        else
            this.targetAngle = targetAngle;
    }

    public void setCurrAngle(double currAngle){
        this.currAngle = currAngle;
    }

    public void setAngle(){
        motor.set(ControlMode.Position, unitMan.toTicks(targetAngle));
    }

    public void terminate(){
        targetAngle = 0;
        setAngle();
    }
}
