package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.conveyor.commands.ConveyorShooter;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.Fire;

public class Shoot extends SequentialCommandGroup {

    public Shoot(Conveyor conveyor, Shooter sniper) {
        addCommands(
                new CommandBase() {
                    @Override
                    public void execute() {
                        sniper.setPower(1);
                    }

                    @Override
                    public void end(boolean interrupted) {
                        sniper.setPower(0);
                    }
                }.withTimeout(1),
                new ParallelCommandGroup(
                        new Fire(sniper),
                        new ConveyorShooter(conveyor, Constants.Conveyor.POWER)
                )
        );
    }

}
