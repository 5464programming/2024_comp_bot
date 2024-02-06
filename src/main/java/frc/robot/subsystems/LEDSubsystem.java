package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem extends EntechSubsystem{

    //TODO: test this code

    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(0);
    private static final boolean ENABLED = true;

    @Override
    public void initialize(){}

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }


    public void LEDInit(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
    }

        public void Blue(){
            if(UserPolicy.blueSelect){
                for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 0, 0, 255);
            }
            }
        }

        public void Red(){
          if(UserPolicy.redSelect){
             for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 255, 0, 0);
            }
            }
        }
}
