package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import entech.subsystems.EntechSubsystem;

public class ClimbSubsystem extends EntechSubsystem{
    CANSparkMax leftarm = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(7, MotorType.kBrushless);  
    DigitalInput limitLup = new DigitalInput(1);
    DigitalInput limitRup = new DigitalInput(2);
    DigitalInput limitLdown = new DigitalInput(3);
    DigitalInput limitRdown = new DigitalInput(4);
    private static final boolean ENABLED = true;

    @Override
    public void initialize(){}

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    //TODO: command to move arms, arms seperate, and an auto command based off the navx to balance the robot on the chains 

    public void Climb(String target, String direction) {  
        if(target.equals("moveSeperate")){
            if(direction == "LeftUp"){
                leftarm.set(1);
            }
            else if(direction == "LeftDown"){
                leftarm.set(-1);
            }
            else if(direction == "RightUp"){
                rightarm.set(1);
            }
            else if(direction == "RightDown"){
                rightarm.set(-1);
            }
        }
        else if(target == "moveAuto"){
            if(direction == "AutoUp"){
                
            }
            else if(direction == "AutoDown"){

            }
        }
        else{
            leftarm.set(0);
            rightarm.set(0);
       }
    }
}
