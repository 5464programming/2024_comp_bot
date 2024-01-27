// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * Manages the subsystems and the interactions between them.
 */
public class SubsystemManager {
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final VisionSubsystem visionSubsystem = new VisionSubsystem();

    public SubsystemManager() {
        driveSubsystem.initialize();
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public ShooterSubsystem getShooterSubsystem(){
        return shooterSubsystem;
    }
    
    public IntakeSubsystem getIntakeSubsystem() {
        return intakeSubsystem;
    }

    public ArmSubsystem getArmSubsystem(){
        return armSubsystem;
    }

    public VisionSubsystem gVisionSubsystem(){
        return visionSubsystem;
    }
}
