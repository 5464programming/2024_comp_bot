package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem extends EntechSubsystem{

    //TODO: test this code

    int totalLED = 60;

    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(totalLED);
    private static final boolean ENABLED = true;

    public static String color_choice;

    private static final String kBlueAmplified = "BlueAmplified";
    private static final String kYellowCoop = "YellowCoop";
    private static final String kPinkIntake = "PinkIntake";
    private static final String kWhite = "White";

    @Override
    public void initialize(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
        White();
    }

    public void periodic(){
        color_choice = UserPolicy.LEDselected;
        switch (color_choice) {
            case kYellowCoop:
                YellowCoop();
                break;

            case kBlueAmplified:
                BlueAmplified();
                break;

            case kPinkIntake:
                PinkIntake();
                break;

            case kWhite:
                White();
                break;
        
            default:
                White();
                break;
        }
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
                ledbuffer.setRGB(i, 0, 0, 60);
            }
            ledstrip.setData(ledbuffer);
        }

        public void PinkIntake(){
             for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 255, 192, 203);
            }
            ledstrip.setData(ledbuffer);
        }

        public void White(){
             for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 100, 100, 100);
            }
            ledstrip.setData(ledbuffer);
        }
}
