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
import frc.robot.OI.UserPolicy;
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
    private static final String kPos1 = "Pos1";
    private static final String kPos2 = "Pos2";
    private static final String kPos2non = "Pos2non";
    private static final String kCenter4Piece_nonPar = "Center4Piece_nonPar";
    private static final String kPos3 = "Pos3";
    // private static final String kPos4 = "Pos4";
    private static final String kShoot1 = "Shoot1";
    private static final String kShoot2 = "Shoot2";
    private static final String kShoot3 = "Shoot3";
    private static final String kBackup = "Backup";
    private static final String kCryingInACorner = "CryingInACorner";
    private static final String kPos2_1 = "Pos2_1";
    private static final String kTestAuto = "TestAuto";

    private String wait_selected;
    private final SendableChooser<String> wait_chooser = new SendableChooser<>();
    private static final String k0Seconds = "0Seconds";
    private static final String k1Second = "1Second";
    private static final String k2Seconds = "2Seconds";
    private static final String k3Seconds = "3Seconds";

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

        auto_chooser.addOption("Pos1", kPos1);
        auto_chooser.addOption("Pos2", kPos2);
        auto_chooser.addOption("Pos2non", kPos2non);
        auto_chooser.addOption("Center4Piece_nonPar", kCenter4Piece_nonPar);
        auto_chooser.addOption("Pos3", kPos3);
        // auto_chooser.addOption("Pos4", kPos4);

        auto_chooser.addOption("Shoot1", kShoot1);
        auto_chooser.addOption("Shoot2", kShoot2);
        auto_chooser.addOption("Shoot3", kShoot3);

        auto_chooser.addOption("Backup", kBackup);
        auto_chooser.setDefaultOption("Shoot2", kShoot2);

        auto_chooser.addOption("CryingInACorner", kCryingInACorner);

        auto_chooser.addOption("Pos2_1", kPos2_1);

        auto_chooser.addOption("TestAuto", kTestAuto);

        SmartDashboard.putData("Auto choices", auto_chooser);

        wait_chooser.addOption("0Seconds", k0Seconds);
        wait_chooser.setDefaultOption("0Seconds", k0Seconds);
        wait_chooser.addOption("1Second", k1Second);
        wait_chooser.addOption("2Seconds", k2Seconds);
        wait_chooser.addOption("3Seconds", k3Seconds);

        SmartDashboard.putData("Wait choices", wait_chooser);
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
        SmartDashboard.putData("Wait choices", wait_chooser);

        SmartDashboard.putNumber("wait time", UserPolicy.wait);
    }

    @Override
    public void autonomousInit() {
         wait_selected = wait_chooser.getSelected();

            switch(wait_selected){
                case k0Seconds:
                UserPolicy.wait = 0;
                break;

                case k1Second:
                UserPolicy.wait = 1;
                break;

                case k2Seconds:
                UserPolicy.wait = 2;
                break;

                case k3Seconds:
                UserPolicy.wait = 3;
                break;

                default:
                UserPolicy.wait = 0;
                break;
            }

        auto_selected = auto_chooser.getSelected();

        //TODO: add autonomous options

          switch(auto_selected){
            case kPos1:
            autonomousCommand = commandFactory.Pos1();
            break;

            case kPos2:
            autonomousCommand = commandFactory.Pos2();
            break;

            case kPos2_1:
            autonomousCommand = commandFactory.Pos2_1();
            break;

            case kPos2non:
            autonomousCommand = commandFactory.Pos2non();
            break;

            case kCenter4Piece_nonPar:
            autonomousCommand = commandFactory.Center4Piece_nonPar();
            
            case kPos3:
            autonomousCommand = commandFactory.Pos3();
            break;
            
            // case kPos4:
            // autonomousCommand = commandFactory.Pos4();
            // break;

            case kShoot1:
            autonomousCommand = commandFactory.Shoot1();
            break;

            case kShoot2:
            autonomousCommand = commandFactory.Shoot2();
            break;

            case kShoot3:
            autonomousCommand = commandFactory.Shoot3();
            break;
            
            case kBackup:
            autonomousCommand = commandFactory.Backup();
            break;

            case kCryingInACorner:
            autonomousCommand = commandFactory.CryingInACorner();
            break;

            case kTestAuto:
            autonomousCommand = commandFactory.TestAuto();

            default: 
            // autonomousCommand = commandFactory.Backup();
            break;
            }

<<<<<<< Updated upstream
=======
        wait_selected = wait_chooser.getSelected();

            switch(wait_selected){
                case k0Seconds:
                // autonomousCommand = commandFactory.ZeroSeconds();
                break;

                case k1Second:
                // autonomousCommand = commandFactory.OneSecond();
                break;

                case k2Seconds:
                // autonomousCommand = commandFactory.TwoSeconds();
                break;

                case k3Seconds:
                // autonomousCommand = commandFactory.ThreeSeconds();
                break;

                default:
                break;
            }
            
>>>>>>> Stashed changes
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
