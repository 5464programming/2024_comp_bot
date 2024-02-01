package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem {
    CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);

    private boolean notedected = false;

    public void IntakeRun(String target) {
        if(target == "feed"){
            notedected = searchnote.get();
            intake.set(1);
        if(notedected == true){
            intake.disable();
        }
        }
        else if(target == "assistShoot"){
            intake.set(1);
        }
        else{
            intake.disable();
        }
    }
}
