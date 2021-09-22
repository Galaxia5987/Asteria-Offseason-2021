package frc.robot;

import java.io.*;
import java.util.Scanner;

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
     * Add data of is Motor inverted
     */
    public Intake() {
        motor.setInverted(Ports.Intake.IS_MOTOR_INVERTED);
    }

    /**
     * Set percentage for motor.
     *
     * @param power the speed times the torque.
     */
    public void powerWheels(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    /**
     * A check to see if piston is open or close, true= open, false= close.
     *
     * @return
     */
    public boolean isPistonEngaged() {
        return piston.get();
    }

    /**
     * Set piston position.
     *
     * @param engaged
     */
    public void setPistonMode(boolean engaged) {
        piston.set(!engaged);
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

    @Override
    public void periodic() {
//        getPort();
    }

//    public int getPort() {
//        File file = new File("C:\\Users\\saarz\\Desktop\\text_file.txt");
//        Scanner scanner = null;
//        int lineCount = 0;
//        boolean firstTime = true;
//        while (true) {
//            try {
//                scanner = new Scanner(file);
//                while (scanner.hasNextLine()) {
//                    scanner.nextLine();
//                    lineCount++;
//                    firstTime = false;
//                }
//                scanner = new Scanner(file);
//                for (int i = 0; i < lineCount; i++) {
//                    if (scanner.hasNextLine()) {
//                        String line = scanner.nextLine();
//                        if (line.contains("kP"))
//                            int port = (int) Double.parseDouble(line.substring(line.indexOf(':') + 2, line.length()));
//
//                    }
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}




