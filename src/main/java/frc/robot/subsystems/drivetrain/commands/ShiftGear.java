package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class ShiftGear extends CommandBase {

    private Drivetrain drivetrain;
    private Drivetrain.GearMode mode;

    public ShiftGear(Drivetrain drivetrain, Drivetrain.GearMode mode) {
        this.drivetrain = drivetrain;
        this.mode = mode;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.shiftGear(mode);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
