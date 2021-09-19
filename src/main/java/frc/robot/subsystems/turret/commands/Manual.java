package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Ports;
import frc.robot.RobotContainer;
import frc.robot.subsystems.turret.Turret;

public class Manual extends CommandBase {
    private final Turret turret;
    private final XboxController xbox = new XboxController(1);


    public Manual(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double x = xbox.getX(GenericHID.Hand.kRight);
        if (Math.abs(x) < 0.01) x = 0;
        x = x / 2;
        turret.setPower(x);
        System.out.println(turret.getTicks());
    }

    @Override
    public void end(boolean interrupted) {
        turret.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
