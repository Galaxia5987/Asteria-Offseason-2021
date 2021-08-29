package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private TalonSRX motor = new TalonSRX(Ports.Intake.MOTOR);
    private Solenoid piston = new Solenoid(Ports.Intake.SOLENOID);

    public void TalonSRXSelenoid() {
        this.motor.setInverted(Ports.Intake.IS_MOTOR_INVERTED);
    }

    public void setMotorPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public boolean isPistonEngaged() {
        return piston.get();
    }

    public void setPistonMode(boolean engaged) {
        piston.set(engaged);
    }

    public void toggle() {
        if (isPistonEngaged()) {
            setPistonMode(false);
        } else {
            setPistonMode(true);
        }
    }

}




