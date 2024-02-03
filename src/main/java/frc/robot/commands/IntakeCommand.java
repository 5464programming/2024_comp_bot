package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends EntechCommand{
        private final IntakeSubsystem intake;

    public IntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.intaking = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.intaking) {
            intake.Intake();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.intaking = false;
        intake.DisableIntake();
    }   
}
