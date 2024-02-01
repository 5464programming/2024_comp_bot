package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.LEDSubsystem;

public class ColorCommand extends EntechCommand {
    
    private final LEDSubsystem led;
    private String target;

    public ColorCommand(LEDSubsystem led, String target) {
        this.led = led;
    }

     @Override
    public void initialize() {
        UserPolicy.colorSelect = true;
    }

    @Override
    public void execute() {
        if (UserPolicy.colorSelect) {
            led.LEDPeriodic(target);
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.colorSelect = false;
    }   
}
