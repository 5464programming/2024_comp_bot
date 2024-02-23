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
        // if (UserPolicy.intaking && UserPolicy.noteHoming) {
            if(UserPolicy.intaking){
            intake.Intake();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.intaking = false;
        intake.DisableIntake();
    }   

    //Returns value of boolean and quits the command when break beam sensor is tripped

    @Override
    public boolean isFinished(){
        if (intake.notenotdected == false) {
            return true;
        }

        else {
            return false;
        }
    }

}
