package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Intake;

public class ToggleIntake extends InstantCommand {

    private Intake intake;
    private boolean value;

    public ToggleIntake(Intake intake, boolean value) {
        this.intake = intake;
        this.value = value;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        super.initialize();
        intake.setPistonMode(value);
    }

}
