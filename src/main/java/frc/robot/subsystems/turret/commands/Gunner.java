package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.subsystems.turret.Turret;

public class Gunner extends CommandBase {
    private double X;
    private double Y;
    private double targetAngle; // Angle the turret needs to be at.
    private Turret gunnerMan; // Turret class object (in order to move the motor).
    private final XboxController xbox = new XboxController(1);
    Trigger LT = new Trigger(() -> xbox.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);

    public Gunner(Turret gunnerMan){
        this.gunnerMan = gunnerMan;
        addRequirements(gunnerMan);
    }

    // Executes all the commands in a loop.
    @Override
    public void execute() {
        X = xbox.getX(GenericHID.Hand.kRight);
        Y = xbox.getY(GenericHID.Hand.kRight);
        targetAngle = Math.atan2(Y, X);


        gunnerMan.setTargetAngle(targetAngle);

        if(LT.get())
            gunnerMan.setPosition();
    }

    // Resets the turret.
    @Override
    public void end(boolean interrupted) {
        gunnerMan.terminate();
    }

    // Continues running the program until interrupted.
    @Override
    public boolean isFinished() {
        return !gunnerMan.anglesEqual();
    }
}
