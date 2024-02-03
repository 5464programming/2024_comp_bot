package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class ClimbSubsystem extends EntechSubsystem{
    CANSparkMax leftarm = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(7, MotorType.kBrushless);  

    RelativeEncoder leftEncoder = leftarm.getEncoder();
    RelativeEncoder rightEncoder = rightarm.getEncoder();

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
            SmartDashboard.putNumber("left encoder", leftEncoder.getVelocity());
            if(leftEncoder.getPosition() > 100){
                leftarm.set(0);
        }
            else{
            leftarm.set(0.5);
        }
        }
            else{
            ClimbLeftDisable();
        }
    }

    
    public void LeftDown(){
        if(UserPolicy.leftDown){
            if(limitLdown.get()){
                leftarm.set(0);
            }
            else{
                leftarm.set(-0.5);
            }
        }
            else{
            ClimbLeftDisable();
        }
    }

    
    public void RightUp(){
        if(UserPolicy.rightUp){
            SmartDashboard.putNumber("right encoder", rightEncoder.getVelocity());
            if(rightEncoder.getPosition() > 100){
                rightarm.set(0);
            }
            else{
            rightarm.set(0.5);
            }
        }
        else{
            ClimbRightDisable();        
        }
    }
    
    public void RightDown(){
        if(UserPolicy.rightDown){
            if(limitRdown.get()){
                rightarm.set(0);
            }
            else{
            rightarm.set(-0.5);
            }
        }
        else{
            ClimbRightDisable();      
        }
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
