package frc.robot.subsystems.colorwheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.colorwheel.ColorWheel;

public class ColorReach extends CommandBase {
    private String currentColor = "";
    private String requestedColor = "";
    private ColorWheel colorWheel;

    public ColorReach(ColorWheel colorWheel, String requestedColor) {
        this.colorWheel = colorWheel;
        this.requestedColor = requestedColor;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        colorWheel.setpower(0.5);
        currentColor = colorWheel.whatColor()
    }

    @Override
    public void end(boolean interrupted) {
        colorWheel.setpower(0);
    }

    @Override
    public boolean isFinished() {
        if (currentColor.equals(requestedColor)) {
            return true;
        } else return false;
    }
}