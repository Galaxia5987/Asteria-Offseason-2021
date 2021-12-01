/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commandgroups.BottomPath;
import frc.robot.commandgroups.PickUpBalls;
import frc.robot.commandgroups.Shoot;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.MinimizeConveyor;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.Gas;
import frc.robot.subsystems.drivetrain.commands.ToggleGear;
import frc.robot.subsystems.drivetrain.commands.ToggleIntakePiston;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.turret.commands.Gunner;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    public static final XboxController xboxControllerOperator = new XboxController(0);
    public static final XboxController xboxControllerDriver = new XboxController(1);
    private final Turret gunnerMan = new Turret();
    Conveyor conveyor = new Conveyor();
    // The robot's subsystems and commands are defined here...
    Funnel funnel = new Funnel();
    Intake intake = new Intake();
    Drivetrain drivetrain = new Drivetrain();
    JoystickButton a = new JoystickButton(xboxControllerOperator, XboxController.Button.kA.value);
    JoystickButton b = new JoystickButton(xboxControllerOperator, XboxController.Button.kB.value);
    JoystickButton y = new JoystickButton(xboxControllerOperator, XboxController.Button.kY.value);
    JoystickButton x = new JoystickButton(xboxControllerOperator, XboxController.Button.kX.value);
    JoystickButton yDrive = new JoystickButton(xboxControllerDriver, XboxController.Button.kY.value);
    Trigger rt = new Trigger(() -> xboxControllerOperator.getTriggerAxis(GenericHID.Hand.kRight) > 0.3);
    Trigger lt = new Trigger(() -> xboxControllerOperator.getTriggerAxis(GenericHID.Hand.kRight) > 0.3);
    JoystickButton rb = new JoystickButton(xboxControllerOperator, XboxController.Button.kBumperRight.value);
    JoystickButton lb = new JoystickButton(xboxControllerOperator, XboxController.Button.kBumperLeft.value);
    //    private final Trigger RT = new Trigger(() -> xboxControllerOperator.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    private Shooter sniper = new Shooter();
    // The robot's subsystems and commands are defined here...

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureButtonBindings();
        drivetrain.setDefaultCommand(new Gas(drivetrain));
        gunnerMan.setDefaultCommand(new Gunner(gunnerMan));
    }


    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        yDrive.whenPressed(new ToggleGear(drivetrain));
        x.whileHeld(new Shoot(conveyor, sniper, 0.7));
        rt.whileActiveContinuous(new Shoot(conveyor, sniper, 1));
        a.whileHeld(new MinimizeConveyor(conveyor, Constants.Conveyor.REVERSE_MOTOR_POWER));
        y.whileHeld(new PickUpBalls(conveyor, funnel, intake));
        b.whileHeld(new Shoot(conveyor, sniper, 0.4));
        lb.whenPressed(new ToggleIntakePiston(intake));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new BottomPath(
                drivetrain, sniper, intake, conveyor, funnel
        );
    }
}
