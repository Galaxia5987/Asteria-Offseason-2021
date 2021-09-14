package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;

/**
 * This class fires the shooter
 * sniper - this variable is the shooter
 * distance - this is the target distance
 * targetSpeed - this is the speed the motors
 * need to get to
 */
public class Fire extends CommandBase {
    private Shooter sniper;
    private double distance;
    private double targetSpeed;

    /**
     * Constructor
     * @param sniper is the Shooter
     * @param distance is the speed needed
     */
    public Fire(Shooter sniper, double distance) {
        this.sniper = sniper;
        this.distance = distance;
        addRequirements(sniper);
    }

    /**
     * This function simply gets the value
     * for the target speed
     */
    @Override
    public void initialize() {
        targetSpeed = getTargetSpeed(distance);
    }

    /**
     * This function runs in a loop and sets
     * the velocity required according to the
     * Shooter class function - setVelocity
     */
    @Override
    public void execute() {
        sniper.setVelocity(targetSpeed);
    }

    /**
     * This finishes the program
     * @param interrupted is self explanatory
     */
    @Override
    public void end(boolean interrupted) {
        sniper.terminate();
    }

    /**
     * While the program is running,
     * it will not be finished and hence:
     * @return is always false
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * This function gets the target speed
     * required for the motors according to
     * the distance the ball needs to travel
     * @param distance is how far the ball will fly
     * @return the speed in rpm
     */
    public double getTargetSpeed(double distance){
        return 0;
    }
}
