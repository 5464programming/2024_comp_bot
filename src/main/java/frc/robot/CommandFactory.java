package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.commands.GyroReset;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

@SuppressWarnings("unused")
public class CommandFactory {
    private DriveSubsystem driveSubsystem;

    public CommandFactory(SubsystemManager subsystemManager) {
        this.driveSubsystem = subsystemManager.getDriveSubsystem();
    }


    public Command AutonomousRun(String autoName){
        SequentialCommandGroup auto = new SequentialCommandGroup();
        auto.addCommands(new WaitCommand(UserPolicy.wait));
        auto.addCommands(new GyroReset(driveSubsystem));

        switch(autoName){
            case Robot.kPos1:
            auto.addCommands(new PathPlannerAuto("Pos1"));
            break;

            case Robot.kPos1Amp:
            auto.addCommands(new PathPlannerAuto("Pos1Amp"));
            break;

            case Robot.kPos2:
            auto.addCommands(new PathPlannerAuto("Pos2"));
            break;
            
            case Robot.kPos2non:
            auto.addCommands(new PathPlannerAuto("Pos2non"));
            break;

            case Robot.kPos2_1:
            auto.addCommands(new PathPlannerAuto("Pos2_1"));
            break;

            case Robot.kPos2Amp:
            auto.addCommands(new PathPlannerAuto("Pos2Amp"));
            break;

            case Robot.kPos3:
            auto.addCommands(new PathPlannerAuto("Pos3"));
            break;

            case Robot.kPos3Far:
            auto.addCommands(new PathPlannerAuto("Pos3Far"));
            break;

            case Robot.kPos3ReallyFar:
            auto.addCommands(new PathPlannerAuto("Pos3ReallyFar"));
            break;

            case Robot.kShoot1:
            auto.addCommands(new PathPlannerAuto("Shoot1"));
            break;

            case Robot.kShoot2:
            auto.addCommands(new PathPlannerAuto("Shoot2"));
            break;

            case Robot.kShoot3:
            auto.addCommands(new PathPlannerAuto("Shoot3"));
            break;

            case Robot.kCenter4Piece_nonPar:
            auto.addCommands(new PathPlannerAuto("Center4Piece_nonPar"));
            break;

            case Robot.kTestAuto:
            auto.addCommands(new PathPlannerAuto("TestAuto"));
            break;

            case Robot.kBackup:
            auto.addCommands(new PathPlannerAuto("Backup"));
            break;

            case Robot.kCryingInACorner:
            auto.addCommands(new PathPlannerAuto("CryingInACorner"));
            break;

            default:
            auto.addCommands(new PathPlannerAuto("Backup"));
            break;
        }
        return auto;
    }
}
