package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Intake;

public class ToggleIntake extends InstantCommand {

    private Intake intake;

    public ToggleIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
        intake.toggle();
    }

}
