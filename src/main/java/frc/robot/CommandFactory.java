package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

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

    public Command Pos1(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Pos1"));
        
        return auto;
    }
    
    public Command Pos2(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Pos2"));
     
        return auto;
    }

    public Command Pos2_1(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Pos2_1"));
     
        return auto;
    }

    public Command Center4Piece_nonPar(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Center4Piece_nonPar"));
     
        return auto;        
    }

    public Command Pos2non(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Pos2non"));
     
        return auto;
    }

    public Command Pos3(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Pos3"));
        
        return auto;
    }

    // public Command Pos4(){
    //     SequentialCommandGroup auto = new SequentialCommandGroup();

    //     auto.addCommands(new GyroReset(driveSubsystem));
    //     auto.addCommands(new PathPlannerAuto("Pos4"));
        
    //     return auto;
    // }

    public Command Shoot1(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Shoot1"));
        
        return auto;
    }

    public Command Shoot2(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Shoot2"));
        
        return auto;
    }

    public Command Shoot3(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Shoot3"));
        
        return auto;
    }

    public Command Backup(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Backup"));
        
        return auto;
    }

    public Command CryingInACorner(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new WaitCommand(UserPolicy.wait));

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("CryingInACorner"));
        
        return auto;
    }


    // The String argument within this one is meant to be changed to test weird auto routines
    // Things that could be tested:
    // DriveByIntakeTest
    // SlowNote8Test
    public Command TestAuto(){
        SequentialCommandGroup auto = new SequentialCommandGroup();
        auto.addCommands(new WaitCommand(UserPolicy.wait));
        auto.addCommands(new GyroReset(driveSubsystem));

        auto.addCommands(new PathPlannerAuto("DriveByIntakeTest"));

        return auto;
    }
}
