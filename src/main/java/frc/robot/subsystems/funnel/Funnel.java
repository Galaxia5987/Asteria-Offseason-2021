package frc.robot.subsystems.funnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Funnel extends SubsystemBase {

    public final TalonSRX motor = new TalonSRX(Ports.Motor.MOTOR);

    private void setMotorPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    private void setMotorInverted() {
        motor.setInverted(Ports.Motor.IS_MOTOR_INVERTED);

    }


}

