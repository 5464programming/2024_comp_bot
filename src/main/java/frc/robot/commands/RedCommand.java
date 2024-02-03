package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.LEDSubsystem;

public class RedCommand extends EntechCommand {
    
    private final LEDSubsystem led;

    public RedCommand(LEDSubsystem led) {
        this.led = led;
    }

     @Override
    public void initialize() {
        UserPolicy.redSelect = true;
    }

    @Override
    public void execute() {
        if (UserPolicy.redSelect) {
            led.Red();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.redSelect = false;
    }   
}
