package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(Ports.Conveyor.MOTOR_POWER);
    private final Solenoid stopper = new Solenoid(Ports.Conveyor.SOLENOID);
    private final AnalogInput funnelProximity = new AnalogInput(Ports.Conveyor.SHOOTER_PROXIMITY);
    private final AnalogInput shooterProximity = new AnalogInput(Ports.Conveyor.FUNNEL_PROXIMITY);

    private void setMotor(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    private void setMotorInverted() {

        motor.setInverted(Ports.Conveyor.IS_MOTOR_INVERTED);
    }

    public boolean isStopperOpen() {
        return stopper.get();
    }

    public void setStopperMode(boolean open) {
        stopper.set(open);
    }

    @Override
    public void periodic() {
        
    }
}

