package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class ShiftGear extends InstantCommand {

    private Drivetrain drivetrain;
    private Drivetrain.GearMode mode;

    /**
     *
     * @pa
     * @param mode
     */
    public ShiftGear(Drivetrain drivetrain, Drivetrain.GearMode mode) {
        this.drivetrain = drivetrain;
        this.mode = mode;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.shiftGear(mode);
    }}

