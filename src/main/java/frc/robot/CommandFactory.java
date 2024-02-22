package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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

    public Command BluePos1(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BluePos1"));
        
        return auto;
    }
    
    public Command BluePos2(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BluePos2"));
     
        return auto;
    }

    public Command BluePos2non(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BluePos2non"));
     
        return auto;
    }

    public Command BluePos3(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BluePos3"));
        
        return auto;
    }

    // public Command BluePos4(){
    //     SequentialCommandGroup auto = new SequentialCommandGroup();

    //     auto.addCommands(new GyroReset(driveSubsystem));
    //     auto.addCommands(new PathPlannerAuto("BluePos4"));
        
    //     return auto;
    // }

    public Command BlueShoot1(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BlueShoot1"));
        
        return auto;
    }

    public Command BlueShoot2(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BlueShoot2"));
        
        return auto;
    }

    public Command BlueShoot3(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("BlueShoot3"));
        
        return auto;
    }

    public Command Backup(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("Backup"));
        
        return auto;
    }

    public Command CryingInACorner(){
        SequentialCommandGroup auto = new SequentialCommandGroup();

        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto("CryingInACorner"));
        
        return auto;
    }
}
