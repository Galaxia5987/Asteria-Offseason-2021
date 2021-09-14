package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Conveyor extends CommandBase {
    public final TalonSRX motor = new TalonSRX(Ports.Conveyor.MOTOR_POWER);

    private void setMotor (double power){
        motor.set(ControlMode.PercentOutput, power);
    }

    private void setMotorInverted(){
        motor.setInverted(Ports.Conveyor.IS_MOTOR_INVERTED);
    }
}

