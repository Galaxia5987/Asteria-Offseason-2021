package frc.robot.subsystems.colorwheel.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ColorWheel.*;
import frc.robot.subsystems.colorwheel.ColorWheel;

public class RotationControl extends CommandBase {

    private ColorWheel colorWheel;
    private String startingColor = "";
    private String lastColor = "";
    private String currentColor = "";
    private double counter = 0;


    public RotationControl(ColorWheel colorWheel) {
        this.colorWheel = colorWheel;
        addRequirements(colorWheel);
    }

    @Override
    public void initialize() {
        startingColor = colorWheel.whatColor();
        lastColor = colorWheel.whatColor();
    }

    @Override
    public void execute() {
        // counting rotations method
        colorWheel.setPower(Constants.ColorWheel.power);
        currentColor = colorWheel.whatColor();
        if (!currentColor.equals("UNKNOWN")) {
            if (!currentColor.equals(lastColor)) {
                lastColor = currentColor;
                if (currentColor.equals(startingColor)) {
                    counter += 0.5;
                }
            }
        }


    }


    @Override
    public boolean isFinished() {
        if (counter >= 3) {
            return true;
        } else
            return false;
    }

    @Override
    public void end(boolean interrupted) {
        colorWheel.setPower(0);
    }
}
