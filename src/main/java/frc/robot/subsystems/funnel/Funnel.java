package frc.robot.subsystems.funnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Name new TalonSRX and input his data from Ports.
 */
public class Funnel extends SubsystemBase {

    private final VictorSPX motor = new VictorSPX(Ports.Funnel.MOTOR);

    /**
     * motor boolean inverted input data from Ports.
     */

    public Funnel() {
        motor.setInverted(Ports.Funnel.IS_MOTOR_INVERTED);

    }

    /**
     * set motor power [%]
     *
     * @param power the speed times the torque.
     */
    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

}




