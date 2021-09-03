package frc.robot.subsystems.funnel.commandush;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add motor to PowerWheels class.
 * Add addRequirements.
 */
public class PowerWheels extends CommandBase {
    private final TalonSRX motor;

    public PowerWheels(TalonSRX motor) {
        this.motor = motor;
        addRequirements((Subsystem) motor);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }


}
