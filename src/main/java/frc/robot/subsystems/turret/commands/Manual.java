package frc.robot.subsystems.turret.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;

public class Manual extends CommandBase {
    private final Turret turret;
    private final XboxController xbox = new XboxController(0);


    public Manual(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double x = -xbox.getX(GenericHID.Hand.kRight);
        if (Math.abs(x) < 0.01) x = 0;
        x /= 4;
        System.out.println(turret.getTicks());
        System.out.println(x);
        turret.setPower(x);
//        turret.setVelocity(x * 300);
//        turret.setPosition(1600);
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
