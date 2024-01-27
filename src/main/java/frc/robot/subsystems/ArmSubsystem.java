package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ArmSubsystem {
    CANSparkMax leftarm = new CANSparkMax(6, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(17, MotorType.kBrushless);   
}
