package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class ClimbSubsystem extends EntechSubsystem{
    CANSparkMax leftarm = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(7, MotorType.kBrushless);  

    RelativeEncoder leftEncoder;
    RelativeEncoder rightEncoder;

    private static final boolean ENABLED = true;

    @Override
    public void initialize(){
        leftEncoder = leftarm.getEncoder();
        rightEncoder = rightarm.getEncoder();

        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public void periodic(){
        SmartDashboard.putNumber("left encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("right encoder", rightEncoder.getPosition());
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    public void LeftUp(){
        if(UserPolicy.leftUp){
            if(leftEncoder.getPosition() < -13){
                leftarm.set(0);
        }
            else{
            leftarm.set(-1);
            }
        }
            else{
            ClimbLeftDisable();
        }
    }

    
    public void LeftDown(){
        if(UserPolicy.leftDown){
            if(leftEncoder.getPosition() > -1){
                leftarm.set(0);
            }
            else{
                leftarm.set(1);
            }
        }
            else{
            ClimbLeftDisable();
        }
    }

    
    public void RightUp(){
        if(UserPolicy.rightUp){
            if(rightEncoder.getPosition() < -85){
                rightarm.set(0);
            }
            else{
            rightarm.set(-1);
            }
        }
        else{
            ClimbRightDisable();        
        }
    }
    
    public void RightDown(){
        if(UserPolicy.rightDown){
            if(rightEncoder.getPosition() > -1){
                rightarm.set(0);
            }
            else{
            rightarm.set(1);
            }
        }
        else{
            ClimbRightDisable();      
        }
    }

    //TODO: if there is extra time then create an automatic climbing method using the NAVX
    
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
