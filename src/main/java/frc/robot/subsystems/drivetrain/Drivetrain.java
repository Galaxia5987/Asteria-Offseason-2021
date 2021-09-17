package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;

public class Drivetrain extends SubsystemBase {
    private TalonFX frMotor = new TalonFX(Ports.Drivetrain.FR);
    private TalonFX rrMotor = new TalonFX(Ports.Drivetrain.RR);
    private TalonFX flMotor = new TalonFX(Ports.Drivetrain.FL);
    private TalonFX rlMotor = new TalonFX(Ports.Drivetrain.RL);
    private Solenoid piston = new Solenoid(0);
    private UnitModel highGear = new UnitModel(0);
    private UnitModel lowGear = new UnitModel(0);


    public Timer timer = new Timer();

    public Drivetrain() {
        rrMotor.setInverted(Ports.Drivetrain.REVERSER_RR);
        frMotor.setInverted(Ports.Drivetrain.REVERSER_FR);
        flMotor.setInverted(Ports.Drivetrain.REVERSER_FL);
        rlMotor.setInverted(Ports.Drivetrain.REVERSER_RL);

        rrMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SR1);
        frMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SR2);
        flMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SF1);
        rlMotor.setSensorPhase(Ports.Drivetrain.REVERSER_SF2);

        rlMotor.follow(flMotor);
        rrMotor.follow(frMotor);

        starTimer();
    }


    public void setPowerR(double valueMotorR) {
        frMotor.set(ControlMode.PercentOutput, valueMotorR);
    }

    public void setPowerL(double valueMotorL) {
        flMotor.set(ControlMode.PercentOutput, valueMotorL);
    }

    public double getVelocityRight() {
        return getUnitModel().toVelocity(frMotor.getSelectedSensorVelocity());
    }

    public double getVelocityLeft() {
        return getUnitModel().toVelocity(frMotor.getSelectedSensorVelocity());
    }

    public UnitModel getUnitModel() {
        if (isHighGear()) {
            return highGear;
        }
        return lowGear;
    }

    public void shiftGear(GearMode mode) {
        switch (mode) {
            case HIGH:
                if (canShiftLow()) {
                    shiftLow();
                }
                break;
            case LOW:
                if (canShiftHigh()) {
                    shiftHigh();
                }
                break;
        }
    }

    public boolean isHighGear() {
        return piston.get();
    }

    public void shiftHigh() {
        starTimer();
        piston.set(true);
    }

    public void shiftLow() {
        starTimer();
        piston.set(false);
    }

    public void toggle() {
        piston.set(!piston.get());
    }

    public boolean canShiftLow() {
        return getVelocityLeft() < Constants.Drivetrain.SHIFT_SPEED_TOLERANCE &&
                getVelocityRight() < Constants.Drivetrain.SHIFT_SPEED_TOLERANCE &&
                isHighGear() &&
                timer.hasElapsed(Constants.Drivetrain.SHIFTER_COOLDOWN);

    }

    public boolean canShiftHigh() {
        return !isHighGear() && timer.hasElapsed(Constants.Drivetrain.SHIFTER_COOLDOWN);
    }

    public void starTimer() {
        timer.reset();
        timer.start();
    }

    public enum GearMode {
        HIGH, LOW
    }


    @Override
    public void periodic() {

    }
}

