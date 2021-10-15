package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.turret.Turret;

import static frc.robot.Constants.Turret.DEADBAND;
import static frc.robot.Constants.Turret.MIN_JOYSTICK_DISTANCE;

public class Gunner extends CommandBase {
    private double X;
    private double Y;
    private double targetAngle; // Angle the turret needs to be at.
    private Turret gunnerMan; // Turret class object (in order to move the motor).
    private final XboxController xbox = RobotContainer.xboxControllerOperator;

    public Gunner(Turret gunnerMan) {
        this.gunnerMan = gunnerMan;
        addRequirements(gunnerMan);
    }

    // Executes all the commands in a loop.
    @Override
    public void execute() {
        X = xbox.getX(GenericHID.Hand.kRight);
        Y = -xbox.getY(GenericHID.Hand.kRight);
        if (Math.abs(X) < DEADBAND)
            X = 0;
        if (Math.abs(Y) < DEADBAND)
            Y = 0;
        targetAngle = Math.toDegrees(Math.atan2(Y, X));
        if (Math.hypot(X, Y) > MIN_JOYSTICK_DISTANCE)
            gunnerMan.setTargetAngle(targetAngle);

//        if(LT.get())
    }

    // Resets the turret.
    @Override
    public void end(boolean interrupted) {
        gunnerMan.terminate();
    }

    // Continues running the program until interrupted.
    @Override
    public boolean isFinished() {
//        return gunnerMan.anglesEqual();
        return false;
    }
}
