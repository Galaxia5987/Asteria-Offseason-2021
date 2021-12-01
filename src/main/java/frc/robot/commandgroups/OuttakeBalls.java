package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.MinimizeConveyor;
import frc.robot.subsystems.funnel.Funnel;
import frc.robot.subsystems.funnel.commandush.PowerWheels;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.commands.Commandy;

public class OuttakeBalls extends ParallelCommandGroup {

    public OuttakeBalls(Funnel funnel, Conveyor conveyor, Intake intake) {
        addCommands(
                new Commandy(intake, -0.4),
                new MinimizeConveyor(conveyor, -0.6),
                new PowerWheels(funnel, -0.4)
        );
    }

}
