package frc.robot.subsystems.funnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Name new TalonSRX and input his data from Ports.
 */
public class Funnel extends SubsystemBase {

    public final TalonSRX motor = new TalonSRX(Ports.Motor.MOTOR);

    /**
     * set motor power [%]
     * @param power the speed times the torque.
     */
    private void setMotorPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    /**
     * motor boolean inverted input data from Ports.
     */

    private void setMotorInverted() {
        motor.setInverted(Ports.Motor.IS_MOTOR_INVERTED);

    }


}

