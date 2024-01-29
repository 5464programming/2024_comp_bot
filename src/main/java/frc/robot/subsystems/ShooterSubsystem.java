package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ShooterSubsystem {
    CANSparkMax shootLeft = new CANSparkMax(0, MotorType.kBrushless);
    CANSparkMax shootRight = new CANSparkMax(1, MotorType.kBrushless);

    public void Shoot() {
        shootLeft.getEncoder();
        shootRight.getEncoder();

        
        //TODO: make command to swap between shooter RPMS
    }

}