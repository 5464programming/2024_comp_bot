package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;

public class ClimbOverride extends EntechCommand {
    @Override
    public void initialize() {
        UserPolicy.climbOverride = true;
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.climbOverride = false;
    }
}
