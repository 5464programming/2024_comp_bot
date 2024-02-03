package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

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

    public void LeftUp(){
        if(UserPolicy.leftUp){
            leftarm.set(0.5);
        }
        else{
            ClimbLeftDisable();
        }
    }

    
    public void LeftDown(){
        if(UserPolicy.leftDown){
            leftarm.set(-0.5);
        }
        else{
            ClimbLeftDisable();
        }
    }

    
    public void RightUp(){
        if(UserPolicy.rightUp){
            rightarm.set(0.5);
        }
        else{
            ClimbRightDisable();        }
    }
    
    public void RightDown(){
        if(UserPolicy.rightDown){
            rightarm.set(-0.5);
        }
        else{
            ClimbRightDisable();        }
    }

    public void AutoUp(){
        if(UserPolicy.autoUp){
        }
        else{
            ClimbLeftDisable();
            ClimbRightDisable();
        }
    }

    public void AutoDown(){
        if(UserPolicy.autoDown){
        }
        else{
            ClimbLeftDisable();
            ClimbRightDisable();
        }
    }

    public void ClimbLeftDisable(){
        leftarm.set(0);
    }

    public void ClimbRightDisable(){
        rightarm.set(0);
    }
}
