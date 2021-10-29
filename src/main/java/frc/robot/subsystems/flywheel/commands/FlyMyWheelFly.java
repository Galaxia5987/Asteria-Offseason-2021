package frc.robot.subsystems.flywheel.commands;

import edu.wpi.first.wpilibj.estimator.KalmanFilter;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.Matrix;
import edu.wpi.first.wpiutil.math.Nat;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N1;
import frc.robot.Constants;
import frc.robot.subsystems.flywheel.Flywheel;

import static frc.robot.Constants.Kalman.*;

public class FlyMyWheelFly extends CommandBase {
    private double currVelocity;
    private double reqDistance;
    private double reqVelocity;
    private Flywheel flywheel = new Flywheel();
    private Vector<N1> A = new Vector<>(Nat.N1());
    private Vector<N1> B = new Vector<>(Nat.N1());
    LinearSystem<N1, N1, N1> stateSpace = new LinearSystem<>(A, B, Matrix.eye(Nat.N1()), new Matrix<>(Nat.N1(), Nat.N1()));
    private KalmanFilter<N1, N1, N1> kalmanFilter = new KalmanFilter<>(Nat.N1(), Nat.N1(), stateSpace,
            VecBuilder.fill(MODEL_TOLERANCE),
            VecBuilder.fill(SENSOR_TOLERANCE),
            Constants.LOOP_PERIOD);
    private Matrix<N1, N1> u = new Matrix<>(Nat.N1(), Nat.N1());
    private Matrix<N1, N1> y = new Matrix<>(Nat.N1(), Nat.N1());

    public FlyMyWheelFly(double reqDistance) {
        this.reqDistance = reqDistance;
        A.fill(-kV / kA);
        B.fill(1 / kA);
    }

    @Override
    public void initialize() {
        reqVelocity = flywheel.calcVelocity(reqDistance);
    }

    @Override
    public void execute() {
        currVelocity = flywheel.getVelocity();

        u.set(1, 1, kalmanFilter.getK().get(1, 1));
        y.set(1, 1, flywheel.getVelocity());

        kalmanFilter.correct(u, y);
        kalmanFilter.predict(u, Constants.LOOP_PERIOD);

        flywheel.setVelocity(y.get(1, 1));
    }

    @Override
    public void end(boolean interrupted) {
        flywheel.terminate();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
