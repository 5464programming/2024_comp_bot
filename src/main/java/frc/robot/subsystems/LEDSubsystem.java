package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSubsystem {
    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(0);

    public void LEDInit(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
    }

    public void LEDPeriodic(String colorChoice){
        String color = colorChoice.toLowerCase();
        if(color.contains("red")){
            for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 255, 0, 0);
            }
        }
        else if(color.contains("blue")){
            for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 0, 0, 255);
            }
        }
    }
}
