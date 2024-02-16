package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.LEDSubsystem;

public class YellowCoopCommand extends EntechCommand {
    
    private final LEDSubsystem led;

    public YellowCoopCommand(LEDSubsystem led) {
        this.led = led;
    }

     @Override
    public void initialize() {
        UserPolicy.LEDselected = "YellowCoop";
    }

    @Override
    public void execute() {
        if (UserPolicy.LEDselected == "YellowCoop") {
            led.YellowCoop();;
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
    }   
}
