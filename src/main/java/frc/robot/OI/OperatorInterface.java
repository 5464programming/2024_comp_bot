package frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import entech.util.EntechJoystick;
import frc.robot.CommandFactory;
import frc.robot.RobotConstants;
import frc.robot.SubsystemManager;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.ClimbCommand;
//import frc.robot.commands.AmpRPMCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.GyroReset;
import frc.robot.commands.IntakeCommand;
//import frc.robot.commands.SpeakerRPMCommand;
import frc.robot.commands.XCommand;
//import frc.robot.commands.AmpRPMCommand;
//import frc.robot.commands.SpeakerRPMCommand;

@SuppressWarnings("unused")
public final class OperatorInterface {
    // private static final EntechJoystick driveJoystick = new EntechJoystick(RobotConstants.Ports.CONTROLLER.JOYSTICK);
    private static final EntechJoystick operatorPanel = new EntechJoystick(RobotConstants.Ports.CONTROLLER.PANEL);
    private static final CommandJoystick driveController = new CommandJoystick(0);
    private static final CommandJoystick secondaryController = new CommandJoystick(1);

    /**
     * Connects commands to operator panel and joystick.
     * 
     * 
     * @param commandFactory
     * @param subsystemManager
     */
    public static void create(CommandFactory commandFactory, SubsystemManager subsystemManager) {
        // TODO: Make these buttons reflect real buttons we would like to press

        //Driver controller
        driveController.button(4).onTrue(new GyroReset(subsystemManager.getDriveSubsystem()));
        driveController.button(3).onTrue(new XCommand());
        
        driveController.button(1).whileTrue(new ShootCommand(subsystemManager.getShooterSubsystem(), "speaker"));
        driveController.button(2).whileTrue(new ShootCommand(subsystemManager.getShooterSubsystem(), "amp"));

        driveController.axisGreaterThan(2, 0.1).whileTrue(new IntakeCommand(subsystemManager.getIntakeSubsystem(), "feed"));
        driveController.axisGreaterThan(3, 0.1).whileTrue(new IntakeCommand(subsystemManager.getIntakeSubsystem(), "assistShoot"));

        //Secondary controller
        secondaryController.button(3).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveSeperate", "LeftUp"));
        secondaryController.button(1).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveSeperate", "LeftDown"));
        secondaryController.button(4).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveSeperate", "RightUp"));
        secondaryController.button(2).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveSeperate", "RightDown"));

        secondaryController.axisGreaterThan(1, 0.1).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveAuto", "AutoUp"));
        secondaryController.axisLessThan(1, -0.1).whileTrue(new ClimbCommand(subsystemManager.getClimbSubsystem(), "moveAuto", "AutoDown"));

        // driveJoystick.WhilePressed(1, new TwistCommand()); // used for actual joystick
        // driveJoystick.WhenPressed(11, new GyroReset(subsystemManager.getDriveSubsystem()));
        // driveJoystick.WhenPressed(9, new XCommand());

        subsystemManager.getDriveSubsystem()
                .setDefaultCommand(new DriveCommand(subsystemManager.getDriveSubsystem(), driveController));
    }

    private OperatorInterface() {
    }
}