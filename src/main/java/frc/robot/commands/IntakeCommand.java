package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends EntechCommand{
        private final IntakeSubsystem intake;
        private String target;

    public IntakeCommand(IntakeSubsystem intake, String target) {
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.intaking = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.intaking) {
            intake.IntakeRun(target);
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.shootr = false;
    }   
}
