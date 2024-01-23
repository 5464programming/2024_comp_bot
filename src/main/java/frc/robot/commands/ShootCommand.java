package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;

    public ShootCommand(ShooterSubsystem shoot, String target) {
        this.shoot = shoot;
    }

     @Override
    public void initialize() {
        UserPolicy.shootr = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.shootr) {
            shoot.AmpRPM();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.shootr = false;
    }   
}