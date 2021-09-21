package frc.robot.subsystems.conveyor.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.conveyor.Conveyor;

/**
 * Create a constructor for conveyor, power.
 * Add requirements.
 */
public class LoadConveyor extends CommandBase {
    private final Conveyor conveyor;
    private final double power;

    public LoadConveyor(Conveyor conveyor, double power) {
        this.conveyor = conveyor;
        this.power = power;
        addRequirements(conveyor);

    }

    @Override
    public void initialize() {

    }

    /**
     * Define when to give power to motor while not interrupted.
     */
    @Override
    public void execute() {
//        if (conveyor.senseFunnelBall() && conveyor.getBallNum() <= Constants.Conveyor.MAX_BALLS) {
        if (true) {
            conveyor.setMotor(power);

        } else {
            conveyor.setMotor(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * Stop giving power to the motor.
     * @param interrupted button not pressed.
     */
    @Override
    public void end(boolean interrupted) {
        conveyor.setMotor(0);
    }
}
