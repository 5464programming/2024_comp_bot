package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;
    private final IntakeSubsystem intake;

    // TODO: Make a timer here!

    public SpeakerShootCommand(ShooterSubsystem shoot, IntakeSubsystem intake) {
        this.shoot = shoot;
        this.intake = intake;
    }

     @Override
    public void initialize() {
        // TODO: Start the timer here!
        UserPolicy.speakerShoot = true;
        UserPolicy.shootUptoSpeed = false;
        UserPolicy.snapAprilSpeaker = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.speakerShoot) {
            // TODO: Check the timer here! And if we are in auto, and the timer is high enough, then start feeding!
            // RobotState.isAutonomous() is the method that you could use so it only times out during autonomous.
            
            if (UserPolicy.shootUptoSpeed && UserPolicy.closetospeaker) {
                UserPolicy.feeding = true;
                intake.IntakeFeed();
            }
            shoot.SpeakerCommand();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.speakerShoot = false;
        UserPolicy.feeding = false;
        shoot.DisableShoot();
        intake.DisableIntake();
        // TODO: Sam asked to have the drivetrain always homing even when not shooting. How do we do this?
        // HINT: You may just be able to remove some code around here.....
        UserPolicy.snapAprilSpeaker = false;
    }  

    @Override
    public boolean isFinished(){
        if (intake.notenotdected == true) {
            return true;
        }

        else {
            return false;
        }
    }

}
