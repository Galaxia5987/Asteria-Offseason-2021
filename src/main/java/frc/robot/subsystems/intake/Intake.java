package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add&named motors in Intake.
 */
public class Intake extends SubsystemBase {
    private TalonSRX motor = new TalonSRX(Ports.Intake.MOTOR);
    private Solenoid piston = new Solenoid(Ports.Intake.SOLENOID);

    /**
     * Add motor inverted.
     */
    public void TalonSRXSelenoid() {
        this.motor.setInverted(Ports.Intake.IS_MOTOR_INVERTED);
    }

    /**
     * Set power for motor.
     * @param power the speed times the torque.
     */
    public void setMotorPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    /**
     * A check to see if piston is open or close.
     * @return
     */
    public boolean isPistonEngaged() {
        return piston.get();
    }

    /**
     * Set piston position.
     * @param engaged
     */
    public void setPistonMode(boolean engaged) {
        piston.set(engaged);
    }

    /**
     * Change piston position.
     */
    public void toggle() {
        if (isPistonEngaged()) {
            setPistonMode(false);
        } else {
            setPistonMode(true);
        }

    }

}




