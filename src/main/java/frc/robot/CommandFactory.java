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

    public Command Line1Score() {
        SequentialCommandGroup auto = new SequentialCommandGroup();
        // reset the gyro
        auto.addCommands(new GyroReset(driveSubsystem));
        // wait for two seconds
        auto.addCommands(new WaitCommand(2));
        // aim and score

        // 
        auto.addCommands(new PathPlannerAuto("Line1Score"));
        // wait for 1 year(second)

        // run the path named "line"
        // auto.addCommands(new PathPlannerAuto("Line"));

        return auto;
    }
}
