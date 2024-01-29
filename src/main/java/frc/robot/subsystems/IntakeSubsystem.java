package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem {
    CANSparkMax intake = new CANSparkMax(0, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);

    private static final boolean ENABLED = true;

    private boolean notedected = false;

    public void IntakeRun() {
        if(ENABLED){
            notedected = searchnote.get();
            intake.set(1);
        if(notedected == true){
            intake.disable();
        }
        }
    }
}
