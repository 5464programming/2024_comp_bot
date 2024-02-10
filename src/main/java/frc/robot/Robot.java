// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.OI.OperatorInterface;

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
    private Command autonomousCommand;
    private SubsystemManager subsystemManager;
    private CommandFactory commandFactory;

    private String auto_selected;
    private final SendableChooser<String> auto_chooser = new SendableChooser<>();

    private static final String kBluePos1 = "BluePos1";
    private static final String kBluePos2 = "BluePos2";
    private static final String kBluePos3 = "BluePos3";
    private static final String kBluePos4 = "BluePos4";

    @Override
    public void robotInit() {
        subsystemManager = new SubsystemManager();
        commandFactory = new CommandFactory(subsystemManager);
        OperatorInterface.create(commandFactory, subsystemManager);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        subsystemManager.getClimbSubsystem().periodic();
        subsystemManager.getIntakeSubsystem().periodic();  
        subsystemManager.getVisionSubsystem().periodic(); 
        subsystemManager.getShooterSubsystem().periodic();

        SmartDashboard.putData("Auto choices", auto_chooser);
    }

    @Override
    public void autonomousInit() {
        auto_selected = auto_chooser.getSelected();

        //TODO: add autonomous options
        auto_chooser.addOption("BluePos1", kBluePos1);
        auto_chooser.addOption("BluePos2", kBluePos2);
        auto_chooser.addOption("BluePos3", kBluePos3);
        auto_chooser.addOption("BluePos4", kBluePos4);

          switch(auto_selected){
            case kBluePos1:
            autonomousCommand = commandFactory.BluePos1();
            break;

            case kBluePos2:
            autonomousCommand = commandFactory.BluePos2();
            break;
            
            case kBluePos3:
            autonomousCommand = commandFactory.BluePos3();
            break;
            
            case kBluePos4:
            autonomousCommand = commandFactory.BluePos4();
            break;
            
            default:
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
