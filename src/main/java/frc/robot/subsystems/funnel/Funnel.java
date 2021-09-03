package frc.robot.subsystems.funnel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

    public class Funnel extends SubsystemBase {

        private final talomnsrx motor = new motor(Ports.MOTOR);

        public void setMotorPower(double power){motor.set(ControlMode.PercentOutput, power);}

}

