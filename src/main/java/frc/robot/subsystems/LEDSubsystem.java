package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem {
    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(0);

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
