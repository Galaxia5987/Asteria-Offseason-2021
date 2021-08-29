package frc.robot.subsystems.colorwheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;

public class ColorWheel extends SubsystemBase {
    VictorSPX motor = new VictorSPX(0);
    ColorSensorV3 sensor = new ColorSensorV3(I2C.Port.kMXP);

    private String lastcolor = "";

    public String whatcolor(){
        Color color = sensor.getColor();
        return "";
    }
    public String rotationcolor() {
        return "";
    }

    public void setpower(double powermotor){
        motor.set(ControlMode.PercentOutput, powermotor);
    }

}
