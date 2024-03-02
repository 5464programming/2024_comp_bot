package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem extends EntechSubsystem{

    //TODO: test this code

    int totalLED = 15;

    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(totalLED);
    private static final boolean ENABLED = true;

    @Override
    public void initialize(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
    }

    public void periodic(){
        // switch (UserPolicy.LEDselected) {
        //     case "YellowCoop":
        //         YellowCoop();
        //         break;

        //     case "BlueAmplified":
        //         BlueAmplified();
        //         break;

        //     case "PinkIntake":
        //         PinkIntake();
        //         break;
        
        //     default:
        //         break;
        // }
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }
        
        public void YellowCoop(){
             for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 200, 80, 0);
            }
           ledstrip.setData(ledbuffer); 
        }

        public void BlueAmplified(){
             for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 0, 0, 255);
            }
            ledstrip.setData(ledbuffer);
        }

        public void PinkIntake(){
             for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 255, 192, 203);
            }
            ledstrip.setData(ledbuffer);
        }
}
