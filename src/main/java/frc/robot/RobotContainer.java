/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commandgroups.PickUpBalls;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.ConveyorShooter;
import frc.robot.subsystems.conveyor.commands.MinimizeConveyor;

import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.funnel.commandush.PowerWheels;
import frc.robot.subsystems.intake.Intake;

import static frc.robot.Constants.Funnel.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    Conveyor conveyor = new Conveyor();
    Funnel funnel = new Funnel();
    Intake intake = new Intake();
    XboxController xboxController = new XboxController(1);
    JoystickButton a = new JoystickButton(xboxController, XboxController.Button.kA.value);
    JoystickButton b = new JoystickButton(xboxController, XboxController.Button.kB.value);
    JoystickButton y = new JoystickButton(xboxController, XboxController.Button.kY.value);
    JoystickButton x = new JoystickButton(xboxController, XboxController.Button.kX.value);


    // The robot's subsystems and commands are defined here...

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        a.whileHeld(new PickUpBalls(conveyor, funnel, intake));
        x.whileHeld(new MinimizeConveyor(conveyor, -Constants.Conveyor.POWER));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {

        // An ExampleCommand will run in autonomous
        return null;
    }
}
