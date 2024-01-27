package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem {
    CANSparkMax intake = new CANSparkMax(0, MotorType.kBrushless);

    DigitalInput detectnote = new DigitalInput(0);
}
