package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.ConveyorShooter;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.Fire;

public class Shoot extends ParallelCommandGroup {

    public Shoot(Conveyor conveyor, Shooter sniper) {
        addCommands(
                new ConveyorShooter(conveyor, Constants.Conveyor.POWER),
                new Fire(sniper)
        );
    }

}
