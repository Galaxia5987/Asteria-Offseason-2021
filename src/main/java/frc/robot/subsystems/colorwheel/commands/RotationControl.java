package frc.robot.subsystems.colorwheel.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.colorwheel.ColorWheel;

public class RotationControl extends CommandBase {

    private ColorWheel colorWheel;
    private String startingcolor = "";
    private String lastcolor = "";
    private String currentcolor = "";
    private double counter = 0;


    public RotationControl(ColorWheel colorWheel) {
        this.colorWheel = colorWheel;
    }

    @Override
    public void initialize() {
        startingcolor = colorWheel.whatcolor();
        lastcolor = colorWheel.whatcolor();
        currentcolor = colorWheel.whatcolor();

    }

    @Override
    public void execute() {
        colorWheel.setpower(0.5);
        currentcolor = colorWheel.whatcolor();
        if (!currentcolor.equals(lastcolor)) {
            lastcolor = currentcolor;
            if (currentcolor.equals(startingcolor)){
                counter += 0.5;
            }
        }


    }

    @Override
    public boolean isFinished() {
        if (counter >= 3){
            return true;
        } else
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        colorWheel.setpower(0);
    }
}
