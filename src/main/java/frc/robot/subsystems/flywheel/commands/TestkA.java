package frc.robot.subsystems.flywheel.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.flywheel.Flywheel;

import static frc.robot.Constants.Kalman.kV;

public class TestkA extends CommandBase {
    private double currVelocity = 0;
    private double lastVelocity = 0;
    private Flywheel flywheel = new Flywheel();
    private double output = 0;
    private double dOutput = 0.001;

    private double calc_kA(){
        return (flywheel.getVoltage() - kV * flywheel.getVelocity()) /
                ((currVelocity - lastVelocity) * dOutput + 0.000001);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        currVelocity = flywheel.getVelocity();

        flywheel.setOutput(output);
        output += dOutput;

        lastVelocity = currVelocity;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
