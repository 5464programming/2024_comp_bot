package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import entech.subsystems.EntechSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.OI.UserPolicy;

public class IntakeSubsystem extends EntechSubsystem{
    CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);
    private static final boolean ENABLED = true;
    private boolean notedected = false;

    @Override
    public void initialize(){}

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

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
