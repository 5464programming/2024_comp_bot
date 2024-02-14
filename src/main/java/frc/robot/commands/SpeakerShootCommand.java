package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;
    private final IntakeSubsystem intake;

    public SpeakerShootCommand(ShooterSubsystem shoot, IntakeSubsystem intake) {
        // TODO: Take the intake subsystem in as an argument

        this.shoot = shoot;
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.speakerShoot = true;
        UserPolicy.shootUptoSpeed = false;
        UserPolicy.snapAprilSpeaker = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.speakerShoot) {
            // TODO: Make this run the intake feed when we are up to speed
            if (UserPolicy.shootUptoSpeed) {
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
        shoot.DisableShoot();
        // TODO: Sam asked to have the drivetrain always homing even when not shooting. How do we do this?
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
