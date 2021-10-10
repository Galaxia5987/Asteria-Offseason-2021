package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.estimator.KalmanFilter;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpiutil.math.Matrix;
import edu.wpi.first.wpiutil.math.Nat;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N1;
import frc.robot.Constants;

import static frc.robot.Constants.Kalman.*;

public class Kalman {
    private double currState;
    private double reqState;
    private TalonSRX motor;
    private Vector<N1> A = new Vector<>(Nat.N1());
    private Vector<N1> B = new Vector<>(Nat.N1());
    LinearSystem<N1, N1, N1> stateSpace = new LinearSystem<>(A, B, Matrix.eye(Nat.N1()), new Matrix<>(Nat.N1(), Nat.N1()));
    private KalmanFilter<N1, N1, N1> kalmanFilter = new KalmanFilter<>(Nat.N1(), Nat.N1(), stateSpace,
            VecBuilder.fill(MODEL_TOLERANCE),
            VecBuilder.fill(SENSOR_TOLERANCE),
            Constants.LOOP_PERIOD);
    private Matrix<N1, N1> u = new Matrix<>(Nat.N1(), Nat.N1());
    private Matrix<N1, N1> y = new Matrix<>(Nat.N1(), Nat.N1());

    public Kalman(double reqState, TalonSRX motor) {
        this.motor = motor;
        this.reqState = reqState;
        A.fill(-kV / kA);
        B.fill(1 / kA);
        updateSystem(kalmanFilter);
    }

    public void updateSystem(KalmanFilter<N1, N1, N1> kalmanFilter) {
        if(currState == reqState)
            return;
        else
            currState = motor.getSelectedSensorVelocity();

        u.set(1, 1, kalmanFilter.getK().get(1, 1));
        y.set(1, 1, motor.getSelectedSensorVelocity());

        kalmanFilter.correct(u, y);
        kalmanFilter.predict(u, Constants.LOOP_PERIOD);

        updateSystem(kalmanFilter);
    }
}