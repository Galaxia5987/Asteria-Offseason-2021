package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.funnel.commandush.PowerWheels;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.commands.Commandy;

public class IntakeAndFunnel extends ParallelCommandGroup {
  public IntakeAndFunnel(Intake intake, Funnel funnel){
      addCommands(
              new Commandy(intake, Constants.Intake.POWER),
              new PowerWheels(funnel, 0.4)
      );
  }
}
