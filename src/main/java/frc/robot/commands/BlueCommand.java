package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.LEDSubsystem;

public class BlueCommand extends EntechCommand {
    
    private final LEDSubsystem led;

    public BlueCommand(LEDSubsystem led) {
        this.led = led;
    }

     @Override
    public void initialize() {
        UserPolicy.blueSelect = true;
    }

    @Override
    public void execute() {
        if (UserPolicy.blueSelect) {
            led.Blue();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.blueSelect = false;
    }   
}
