package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class IntakeSubsystem {
    CANSparkMax takeLeft = new CANSparkMax(0, MotorType.kBrushless);
    CANSparkMax takeRight = new CANSparkMax(1, MotorType.kBrushless);
}
