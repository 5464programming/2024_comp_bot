// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.OI.OperatorInterface;
import frc.robot.commands.AmpShootCommand;
import frc.robot.commands.FeedCommand;
import frc.robot.commands.GyroReset;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SpeakerShootCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // Main robot startup things
    private Command autonomousCommand;
    private SubsystemManager subsystemManager;
    private CommandFactory commandFactory;
    
    // Commands to register in Path Planner
    private IntakeCommand intakeCommand;
    private AmpShootCommand ampShootCommand;
    private SpeakerShootCommand speakerShootCommand;
    private FeedCommand feedCommand;
    private GyroReset gyroReset;
    // private BackflipCommand backflipCommand;


    // Building the autonomous chooser
    private String auto_selected;
    private final SendableChooser<String> auto_chooser = new SendableChooser<>();
    private static final String kBluePos1 = "BluePos1";
    private static final String kBluePos2 = "BluePos2";
    private static final String kBluePos2non = "BluePos2non";
    private static final String kCenter4Piece_nonPar = "Center4Piece_nonPar";
    private static final String kBluePos3 = "BluePos3";
    // private static final String kBluePos4 = "BluePos4";
    private static final String kBlueShoot1 = "BlueShoot1";
    private static final String kBlueShoot2 = "BlueShoot2";
    private static final String kBlueShoot3 = "BlueShoot3";
    private static final String kBackup = "Backup";
    private static final String kCryingInACorner = "CryingInACorner";

    @Override
    public void robotInit() {
        subsystemManager = new SubsystemManager();
        intakeCommand = new IntakeCommand(subsystemManager.getIntakeSubsystem());
        ampShootCommand = new AmpShootCommand(subsystemManager.getShooterSubsystem());
        speakerShootCommand = new SpeakerShootCommand(subsystemManager.getShooterSubsystem(), subsystemManager.getIntakeSubsystem());
        feedCommand = new FeedCommand(subsystemManager.getIntakeSubsystem());
        gyroReset = new GyroReset(subsystemManager.getDriveSubsystem());

        NamedCommands.registerCommand("IntakeCommand", intakeCommand);
        NamedCommands.registerCommand("AmpShootCommand", ampShootCommand);
        NamedCommands.registerCommand("SpeakerShootCommand", speakerShootCommand);
        NamedCommands.registerCommand("FeedCommand", feedCommand);
        NamedCommands.registerCommand("GyroReset", gyroReset);

        commandFactory = new CommandFactory(subsystemManager);
        OperatorInterface.create(commandFactory, subsystemManager);

        auto_chooser.addOption("BluePos1", kBluePos1);
        auto_chooser.addOption("BluePos2", kBluePos2);
        auto_chooser.addOption("BluePos2non", kBluePos2non);
        auto_chooser.addOption("Center4Piece_nonPar", kCenter4Piece_nonPar);
        auto_chooser.addOption("BluePos3", kBluePos3);
        // auto_chooser.addOption("BluePos4", kBluePos4);

        auto_chooser.addOption("BlueShoot1", kBlueShoot1);
        auto_chooser.addOption("BlueShoot2", kBlueShoot2);
        auto_chooser.addOption("BlueShoot3", kBlueShoot3);

        auto_chooser.addOption("Backup", kBackup);

        auto_chooser.addOption("CryingInACorner", kCryingInACorner);

        SmartDashboard.putData("Auto choices", auto_chooser);

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        subsystemManager.getClimbSubsystem().periodic();
        subsystemManager.getIntakeSubsystem().periodic();  
        subsystemManager.getVisionSubsystem().periodic(); 
        subsystemManager.getShooterSubsystem().periodic();
        subsystemManager.getLedSubsystem().periodic();

        SmartDashboard.putData("Auto choices", auto_chooser);
    }

    @Override
    public void autonomousInit() {
        auto_selected = auto_chooser.getSelected();

        //TODO: add autonomous options

          switch(auto_selected){
            case kBluePos1:
            autonomousCommand = commandFactory.BluePos1();
            break;

            case kBluePos2:
            autonomousCommand = commandFactory.BluePos2();
            break;

            case kBluePos2non:
            autonomousCommand = commandFactory.BluePos2non();
            break;

            case kCenter4Piece_nonPar:
            autonomousCommand = commandFactory.Center4Piece_nonPar();
            
            case kBluePos3:
            autonomousCommand = commandFactory.BluePos3();
            break;
            
            // case kBluePos4:
            // autonomousCommand = commandFactory.BluePos4();
            // break;

            case kBlueShoot1:
            autonomousCommand = commandFactory.BlueShoot1();
            break;

            case kBlueShoot2:
            autonomousCommand = commandFactory.BlueShoot2();
            break;

            case kBlueShoot3:
            autonomousCommand = commandFactory.BlueShoot3();
            break;
            
            case kBackup:
            autonomousCommand = commandFactory.Backup();
            break;

            case kCryingInACorner:
            autonomousCommand = commandFactory.CryingInACorner();
            break;

            default: 
            // autonomousCommand = commandFactory.Backup();
            break;
            }

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic(){
        }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledInit(){

    }
}
