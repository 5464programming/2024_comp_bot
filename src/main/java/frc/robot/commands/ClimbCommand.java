package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;
    private String target;
    private String direction;

    public ClimbCommand(ClimbSubsystem climb, String target, String direction) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.climbing = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.climbing) {
            climb.Climb(target, direction);
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.climbing = false;
    }   
}
