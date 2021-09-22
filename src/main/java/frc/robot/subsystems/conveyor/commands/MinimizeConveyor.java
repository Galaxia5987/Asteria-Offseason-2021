package frc.robot.subsystems.conveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.conveyor.Conveyor;

/**
 * Create a constructor for conveyor, open.
 * Add requirements.
 */

public class MinimizeConveyor extends CommandBase {
    private final Conveyor conveyor;
    private final double power;

    public MinimizeConveyor(Conveyor conveyor, double power) {
        this.conveyor = conveyor;
        this.power = power;
        addRequirements(conveyor);
    }

    @Override
    public void initialize() {
    }

    /**
     * Reverse the ball when funnel proximity doesn't sense ball.
     */
    @Override
    public void execute() {
        if (conveyor.senseFunnelBall()) {
            conveyor.setMotor(0);
        } else {
            conveyor.setMotor(power);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }


}
