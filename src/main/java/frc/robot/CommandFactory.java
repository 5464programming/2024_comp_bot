package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.GyroReset;
import frc.robot.subsystems.DriveSubsystem;

@SuppressWarnings("unused")
public class CommandFactory {
    private DriveSubsystem driveSubsystem;

    public CommandFactory(SubsystemManager subsystemManager) {
        this.driveSubsystem = subsystemManager.getDriveSubsystem();
    }

    public Command getAutoCommand() {
        SequentialCommandGroup auto = new SequentialCommandGroup();
        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new WaitCommand(2));
        auto.addCommands(new PathPlannerAuto("Line"));
        return auto;
    }
}
