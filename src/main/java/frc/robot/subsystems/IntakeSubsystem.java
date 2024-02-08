package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import entech.subsystems.EntechSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI.UserPolicy;

public class IntakeSubsystem extends EntechSubsystem{

    //TODO: create a command to automatic run the intake once the shooter is up to speed

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

    public void periodic(){
        SmartDashboard.putBoolean("break beam", searchnote.get());
    }

    public void DisableIntake(){
        intake.set(0);
    }

    public void IntakeFeed(){
        if(UserPolicy.feeding){ // JAKE PUT THIS IN
        // if(UserPolicy.feeding && UserPolicy.shootUptoSpeed){ // THIS DOES NOT WORK CURRENTLY
            intake.set(-1);
        }
        else{
            DisableIntake();
        }
        }

    public void IntakeReverse(){
        if(UserPolicy.intakeReverse){
            intake.set(1);
        }
        else{
            DisableIntake();
        }
    }    
        
    public void Intake(){
        if(UserPolicy.intaking){
            notedected = searchnote.get();
           if(notedected == true){
            intake.set(-1);
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
