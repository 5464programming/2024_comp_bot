package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.OI.UserPolicy;

public class IntakeSubsystem {
    CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);

    private boolean notedected = false;

    public void DisableIntake(){
        intake.set(0);
    }

    public void IntakeFeed(){
        if(UserPolicy.feeding){
            intake.set(-0.5);
        }
        else{
            DisableIntake();
        }
        }
        
    public void Intake(){
        if(UserPolicy.intaking){
            notedected = searchnote.get();
           if(notedected == false){
            intake.set(-0.5);
           } 
           else{
            intake.set(0);
           }
        }
        else{
            DisableIntake();
        }
    }
}
