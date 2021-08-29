package frc.robot.subsystems.colorwheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
    VictorSPX motor = new VictorSPX();
    RevColorsSensor sensor = new RevColorSensor();


    private String lastcolor = "";

    public String whatcolor(){
//        sensor.read();
        return "";
    }
    public String rotationcolor() {
        return "";
    }

    public void setpower(double powermotor){
        motor.set(ControlMode.PercentOutput, powermotor);
    }

}
