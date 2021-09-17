package frc.robot.subsystems.conveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;

/**
 * Add new functions- motor, stopper, funnelProximity, shooterProximity, ballNum, newFunnelBall, newShooterBall.
 */
public class Conveyor extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(Ports.Conveyor.MOTOR_POWER);
    private final Solenoid stopper = new Solenoid(Ports.Conveyor.SOLENOID);
    private final AnalogInput funnelProximity = new AnalogInput(Ports.Conveyor.SHOOTER_PROXIMITY);
    private final AnalogInput shooterProximity = new AnalogInput(Ports.Conveyor.FUNNEL_PROXIMITY);
    private int ballNum = Constants.Conveyor.BALL_NUMBER;
    private boolean newFunnelBall = Constants.Conveyor.NEW_FUNNEL_BALL;
    private boolean newShooterBall = Constants.Conveyor.NEW_SHOOTER_BALL;

    /**
     * Set motor inverted.
     */
    public Conveyor() {
        motor.setInverted(Ports.Conveyor.IS_MOTOR_INVERTED);
    }

    /**
     * Set motor power.
     *
     * @param power %
     */
    public void setMotor(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    /**
     * Add boolean stopper open/ close.
     *
     * @return stopper open/ close.
     */
    public boolean isStopperOpen() {
        return stopper.get();
    }

    /**
     * Set stopper mode.
     *
     * @param open is the stopper open.
     */
    public void setStopperMode(boolean open) {
        stopper.set(open);
    }

    /**
     * Get ball number.
     *
     * @return the ball number.
     */
    public int getBallNum() {
        return ballNum;
    }

    /**
     * Is the funnel proximity sense a ball.
     *
     * @return boolean sensed.
     */
    public boolean senseFunnelBall() {
        return funnelProximity.getValue() > Constants.Conveyor.MIN_PROXIMITY_DISTANCE;
    }

    /**
     * Is the shooter proximity sense a ball.
     *
     * @return boolean sensed.
     */
    public boolean senseShooterBall() {
        return shooterProximity.getValue() > Constants.Conveyor.MIN_PROXIMITY_DISTANCE;
    }

    /**
     * Define the ball number.
     */
    @Override
    public void periodic() {
        if (senseFunnelBall()) {
            if (newFunnelBall) {
                ballNum = ballNum + 1;
            }
            newFunnelBall = false;
        } else {
            newFunnelBall = true;
        }

        if (senseShooterBall()) {
            if (newShooterBall) {
                ballNum = ballNum - 1;
            }
            newShooterBall = false;
        } else {
            newShooterBall = true;
        }
    }
}
