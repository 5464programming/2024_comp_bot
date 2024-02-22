package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem extends EntechSubsystem{

    AddressableLED ledstrip = new AddressableLED(0);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(0);
    private static final boolean ENABLED = true;

    // TODO: THIS CLASS NEEDS A CONSTRUCTOR! That is, a method that has the same name as the class.
    // That is why it was crashing code at Blue Twilight!


    @Override
    public void initialize(){}

    public void periodic(){
        switch (UserPolicy.LEDselected) {
            case "YellowCoop":
                YellowCoop();
                break;

            case "BlueAmplified":
                BlueAmplified();
                break;

            case "PinkIntake":
                PinkIntake();
                break;
        
            default:
                break;
        }
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }


    public void LEDInit(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
    }
        
        public void YellowCoop(){
             for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 0, 0, 0);
            }
        }

        public void BlueAmplified(){
             for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 0, 0, 255);
            }
        }

        public void PinkIntake(){
             for(int i = 1; i < 1; i++){
                ledbuffer.setRGB(0, 0, 0, 0);
            }
        }
}
