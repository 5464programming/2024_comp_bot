package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private static final String kClear = "Clear";

    private Timer blinkyTimer = new Timer();  // timer for blinks
    private boolean blinkingActive = false;   // flag for blinking state
    private boolean blinkRunState = false;    // keeps track of the LEDs being on/off
    private double blinkTime = 250;              // seconds that we blink
    private int blinkReps = 5;                // number of blinks that we want
    private double blinkRunReps = 0;

    @Override
    public void initialize(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
        White();
        blinkyTimer.stop();
        blinkyTimer.reset();
        
    }

    public void periodic(){
        SmartDashboard.putBoolean("blinking", blinkingActive);
        if(blinkingActive){
            handleBlinking();
        }
        else{
            color_choice = UserPolicy.LEDselected;
        }
        
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
        
            case kClear:
                Clear();

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
            ledbuffer.setRGB(i, 255, 25, 25);
        }
        ledstrip.setData(ledbuffer);
    }

    public void White(){
            for(int i = 1; i < totalLED; i++){
            ledbuffer.setRGB(i, 100, 100, 100);
        }
        ledstrip.setData(ledbuffer);
    }

    public void Clear(){
        for(int i = 1; i < totalLED; i++){
            ledbuffer.setRGB(i, 0, 0, 0);
        }
        ledstrip.setData(ledbuffer);
    }

    public void startBlinking(){
        blinkyTimer.reset();
        blinkyTimer.start();
        blinkingActive = true;
        blinkRunState = true; //start with the LEDs ON
        blinkRunReps = 0;
    }

    private void handleBlinking(){
        // check the timer, flip if it's time
        if(blinkyTimer.get() >= blinkTime){
            // show a color if the state is true
            if(blinkRunState){
                color_choice = UserPolicy.LEDselected;
            }
            // show nothing if the state is false
            else{
                color_choice = kClear;
            }
            // reset the timer, restart timer
            blinkyTimer.reset();
            blinkyTimer.start();

            // increment our reps, then flip state for next time
            blinkRunReps += 0.5;
            blinkRunState = !blinkRunState;    
        }

        // check the blink reps. Quit blinking if reps are done.
        if(blinkRunReps>=blinkReps){
            blinkyTimer.stop();
            blinkRunState = false;
            blinkingActive = false;
            blinkRunReps = 0;
        }
    }

}
