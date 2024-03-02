package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import entech.commands.EntechCommand;
// import entech.util.EntechJoystick;
import frc.robot.RobotConstants;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class DriveCommand extends EntechCommand {
    private static final double MAX_SPEED_PERCENT = 0.9;

    private final DriveSubsystem drive;
    // private final EntechJoystick joystick;
    private final CommandJoystick driveController;
    // Joystick logi_joystick;
    private final VisionSubsystem vision;

    public DriveCommand(DriveSubsystem drive, VisionSubsystem vision, CommandJoystick driveController) {
        super(drive);
        this.drive = drive;
        this.driveController = driveController;
        this.vision =vision;
    }

    @Override
    public void end(boolean interrupted) {
        drive.drive(0, 0, 0, true, true);
    }

    @Override
    public void execute() {
        // double xRaw = joystick.getX();
        // double yRaw = joystick.getY();
        // double rotRaw = joystick.getZ();
        double xRaw = driveController.getRawAxis(0);
        double yRaw = driveController.getRawAxis(1);
        double rotRaw = -driveController.getRawAxis(4);

        double xConstrained = MathUtil.applyDeadband(MathUtil.clamp(xRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.Ports.CONTROLLER.JOYSTICK_AXIS_THRESHOLD);
        double yConstrained = MathUtil.applyDeadband(MathUtil.clamp(yRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.Ports.CONTROLLER.JOYSTICK_AXIS_THRESHOLD);
        double rotConstrained = MathUtil.applyDeadband(
                MathUtil.clamp(rotRaw, -MAX_SPEED_PERCENT, MAX_SPEED_PERCENT),
                RobotConstants.Ports.CONTROLLER.JOYSTICK_AXIS_THRESHOLD);

        double xSquared = Math.copySign(xConstrained * xConstrained, xConstrained);
        double ySquared = Math.copySign(yConstrained * yConstrained, yConstrained);
        double rotSquared = Math.copySign(rotConstrained * rotConstrained, rotConstrained);

        if (UserPolicy.xLocked) {
            drive.setX();
            return;
        }

        if (UserPolicy.twistable) {
            drive.drive(-ySquared, -xSquared, rotSquared, true, true);
        } 
        else if (UserPolicy.snapAprilSpeaker && vision.targetsPresent){
            double yaw = vision.cameraX;
            drive.drive(-ySquared, -xSquared, -yaw/100, true, true);
        }
        else if(UserPolicy.intaking && vision.notesPresent){
            double noteYaw = vision.noteX;
            drive.drive(-ySquared, -xSquared, -noteYaw/50, true, true);
        }
        // TODO: Uncomment this code and sure this works!
        // This "should" allow auto-homing of the drivetrain to any notes ahead of it.
        // else if (UserPolicy.intaking && vision.notesPresent){
        //     double yaw = vision.noteX;
        //     drive.drive(-ySquared, -xSquared, -yaw/100, true, true);
        // }

        else {
            drive.drive(-ySquared, -xSquared, rotSquared, true, true);
        }


    }

    @Override
    public void initialize() {
        drive.drive(0, 0, 0, true, true);

        drive.brakeMode();

    }

    @Override
    public boolean isFinished() {

        return false;
    }

}
