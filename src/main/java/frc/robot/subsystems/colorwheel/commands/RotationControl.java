package frc.robot.subsystems.colorwheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.colorwheel.ColorWheel;

public class RotationControl extends CommandBase {

    private ColorWheel colorWheel;
    private String startingcolor = "";
    private int counter = 0;

    public RotationControl(ColorWheel colorWheel) {
        this.colorWheel = colorWheel;
    }

    @Override
    public void initialize() {
        startingcolor = colorWheel.whatcolor();

    }

    @Override
    public void execute() {

        colorWheel.setpower(0.5);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
