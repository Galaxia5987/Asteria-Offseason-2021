package frc.robot.subsystems.colorwheel;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;

import static frc.robot.Pot.ColorWheel.*;

public class ColorWheel extends SubsystemBase {
    VictorSPX motor = new VictorSPX(PORT_MOTOR);
    ColorSensorV3 sensor = new ColorSensorV3(I2C.Port.kMXP);
    ColorMatch match = new ColorMatch();

    private String lastColor = "";

    public ColorWheel() {
        match.addColorMatch(Color.kYellow); // yellow
        match.addColorMatch(Color.kGreen); // green
        match.addColorMatch(Color.kRed); // red
        match.addColorMatch(Color.kBlue); // blue
    }

    /**
     *
     * @return the color the sensor sees returned as a String.
     */
    public String whatColor() {
        Color color = sensor.getColor();
        ColorMatchResult result = match.matchClosestColor(color);
        Color resultColor = result.color;


        if (resultColor == Color.kYellow) {
            return "yellow";
        } else if (resultColor == Color.kGreen) {
            return "green";
        } else if (resultColor == Color.kRed) {
            return "red";
        } else if (resultColor == Color.kBlue) {
            return "blue";
        } else {
            return "UNKNOWN";
        }
    }

    /**
     * @param powerMotor - the power given to motor [%].
     * sets the power of the motor
     */
    public void setPower(double powerMotor) {
        motor.set(ControlMode.PercentOutput, powerMotor);
    }


}
