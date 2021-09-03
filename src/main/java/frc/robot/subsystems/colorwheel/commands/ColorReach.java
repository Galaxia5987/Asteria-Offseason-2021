package frc.robot.subsystems.colorwheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.colorwheel.ColorWheel;

public class ColorReach extends CommandBase {
    private String currentcolor = "";
    private String requestedcolor = "";
    private ColorWheel colorwheel;

    public ColorReach(ColorWheel colorWheel, String requestedcolor) {
        this.colorwheel = colorWheel;
        this.requestedcolor = requestedcolor;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        colorwheel.setpower(0.5);
        currentcolor = colorwheel.whatcolor()
    }

    @Override
    public void end(boolean interrupted) {
        colorwheel.setpower(0);
    }

    @Override
    public boolean isFinished() {
        if (currentcolor.equals(requestedcolor)) {
            return true;
        } else return false;
    }
}