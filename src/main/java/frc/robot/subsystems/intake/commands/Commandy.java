package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;

/**
 * Add Commandy function;
 */
public class Commandy extends CommandBase {
    private final Intake intake;

    /**
     * Add Requirements
     * @param intake name to Intake.
     */
    public Commandy(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }



    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
