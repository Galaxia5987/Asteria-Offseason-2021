package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.LoadConveyor;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.funnel.commandush.PowerWheels;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.commands.Commandy;

public class PickUpBalls extends ParallelCommandGroup {

    public PickUpBalls(Conveyor conveyor, Funnel funnel, Intake intake) {
        addCommands(
                new LoadConveyor(conveyor, Constants.Conveyor.POWER),
                new Commandy(intake, Constants.Intake.POWER),
                new PowerWheels(funnel, Constants.Funnel.POWER)
        );
    }
}
