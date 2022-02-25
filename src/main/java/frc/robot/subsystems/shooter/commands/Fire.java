package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.shooter.Shooter;

import static frc.robot.Constants.Shooter.*;

/**
 * This class fires the shooter
 * sniper - this variable is the shooter
 * distance - this is the target distance
 * targetSpeed - this is the speed the motors
 * need to get to
 */
public class Fire extends CommandBase {
    private Shooter sniper;
    private double targetSpeed;

    /**
     * Constructor
     *
     * @param sniper is the Shooter
     */
    public Fire(Shooter sniper) {
        this.sniper = sniper;
        addRequirements(sniper);
    }

    /**
     * This function simply gets the value
     * for the target speed
     */
    @Override
    public void initialize() {
        targetSpeed = SPEED;
    }

    /**
     * This function runs in a loop and sets
     * the velocity required according to the
     * Shooter class function - setVelocity
     */
    @Override
    public void execute() {
        // sniper.setVelocity(targetSpeed);
        sniper.setPower(0.5);
        RobotContainer.xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
    }

    /**
     * This finishes the program
     *
     * @param interrupted is self explanatory
     */
    @Override
    public void end(boolean interrupted) {
        RobotContainer.xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        sniper.terminate();
    }

    /**
     * While the program is running,
     * it will not be finished and hence:
     *
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
     *
     * @param distance is how far the ball will fly
     * @return the speed in rpm
     */
    public double getTargetSpeed(double distance) {
        distance = MathUtil.clamp(distance, 1.4, 11); //The camera can't really see beyond these distances, which means they are most likely erroneous.
        return MathUtil.clamp(.0755 * Math.pow(distance, 4) - 1.38254 * Math.pow(distance, 3) + 8.6493 * Math.pow(distance, 2) - 16.905 * distance + 71.998
                , 50, 120); //Prevent the shooter from speeding up too much, and from not activating.
    }
}
