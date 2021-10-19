/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ExampleSubsystem.ExampleSubsystem;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.DrivetrainDefaultCommand;
import frc.robot.subsystems.drivetrain.commands.GTAdrive;
import frc.robot.subsystems.drivetrain.commands.Gas;
import frc.robot.subsystems.drivetrain.commands.ShiftGear;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static XboxController xboxController = new XboxController(1);
  private final JoystickButton a = new JoystickButton(xboxController, XboxController.Button.kA.value);
  private final JoystickButton b = new JoystickButton(xboxController, XboxController.Button.kB.value);
  public Drivetrain drivetrain = new Drivetrain();


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configDefaultCommands();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    a.whenPressed(new ShiftGear(drivetrain, Drivetrain.GearMode.HIGH));
    b.whenPressed(new ShiftGear(drivetrain, Drivetrain.GearMode.LOW));
  }

  private void configDefaultCommands() {
//    drivetrain.setDefaultCommand(new DrivetrainDefaultCommand(drivetrain));
    drivetrain.setDefaultCommand(new GTAdrive(drivetrain));
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
