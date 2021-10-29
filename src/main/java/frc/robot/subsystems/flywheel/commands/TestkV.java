package frc.robot.subsystems.flywheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.flywheel.Flywheel;

public class TestkV extends CommandBase {
    private Flywheel flywheel = new Flywheel();
    private double output = 0.5;

    private double calc_kV(){
        return flywheel.getVoltage() / flywheel.getVelocity();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        flywheel.setOutput(output);
        System.out.println(calc_kV());
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
