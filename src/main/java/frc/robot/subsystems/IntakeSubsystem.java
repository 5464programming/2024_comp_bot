package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem {
    CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);

    private boolean notedected = false;

    public void IntakeFeed(){
        intake.set(-1);
        }

    public void Intake(){
        notedected = searchnote.get();
        intake.set(1);
        System.out.println("feed");
    if(notedected == true){
            intake.disable();
    }    
    }
}
