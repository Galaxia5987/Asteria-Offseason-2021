package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Intake;

/**
 * Add Commandy function;
 */
public class Commandy extends CommandBase {
    private final Intake intake;
    private final double power;

    /**
     * Add Requirements
     * @param intake name to Intake.
     */
    public Commandy(Intake intake, double power) {
        this.intake = intake;
        this.power = power;
        addRequirements(intake);

    }


    @Override
    public void initialize() {
        super.initialize();
        intake.setPistonMode(false);
    }


    @Override
    public void execute() {
        super.execute();
        intake.powerWheels(power);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }


    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        intake.powerWheels(0);
//        intake.setPistonMode(true);
    }
}
