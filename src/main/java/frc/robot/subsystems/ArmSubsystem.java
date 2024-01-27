package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class ArmSubsystem {
    CANSparkMax leftarm = new CANSparkMax(6, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(17, MotorType.kBrushless);  
    DigitalInput limitLup = new DigitalInput(1);
    DigitalInput limitRup = new DigitalInput(2);
    DigitalInput limitLdown = new DigitalInput(3);
    DigitalInput limitRdown = new DigitalInput(4);
}
