package frc.robot.subsystems.funnel.commandush;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.funnel.Funnel;

/**
 * Add motor to PowerWheels class.
 * Add addRequirements.
 */
public class PowerWheels extends CommandBase {
    private final Funnel funnel;
    private final double power;


    public PowerWheels(Funnel funnel, double power) {
        this.funnel = funnel;
        this.power = power;
        addRequirements(funnel);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        funnel.setPower(power);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        funnel.setPower(0);
    }


}
