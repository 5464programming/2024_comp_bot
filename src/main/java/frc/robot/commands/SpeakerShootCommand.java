package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;

    public SpeakerShootCommand(ShooterSubsystem shoot) {
        this.shoot = shoot;
    }

     @Override
    public void initialize() {
        UserPolicy.speakerShoot = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.speakerShoot) {
            shoot.SpeakerCommand();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.speakerShoot = false;
        
    }   
}
