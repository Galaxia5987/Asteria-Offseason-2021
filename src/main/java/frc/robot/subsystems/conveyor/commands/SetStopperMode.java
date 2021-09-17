package frc.robot.subsystems.conveyor.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.conveyor.Conveyor;

/**
 * Create a constructor for conveyor, open.
 * Add requirements.
 */

public class SetStopperMode extends InstantCommand {
    private final Conveyor conveyor;
    private final boolean open;


    public SetStopperMode(Conveyor conveyor, boolean open) {
        this.conveyor = conveyor;
        this.open = open;

        addRequirements(conveyor);
    }

    /**
     * When not interrupted set stopper mode.
     */
    @Override
    public void initialize() {
        conveyor.setStopperMode(open);
    }
}
