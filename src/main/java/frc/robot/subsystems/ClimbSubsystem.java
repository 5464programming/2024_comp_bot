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

    RelativeEncoder leftEncoder;
    RelativeEncoder rightEncoder;

    DigitalInput limitLdown = new DigitalInput(1);
    DigitalInput limitRdown = new DigitalInput(2);

    private static final boolean ENABLED = true;

    boolean LeftLimitActivated;
    boolean RightLimitActivated;

    boolean LeftZero = true;
    boolean RightZero = true;

    @Override
    public void initialize(){
        leftEncoder = leftarm.getEncoder();
        rightEncoder = rightarm.getEncoder();
    }

    public void periodic(){
        LeftLimitActivated = limitLdown.get();
        RightLimitActivated = limitRdown.get();
        
        SmartDashboard.putNumber("left encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("right encoder", rightEncoder.getPosition());
        SmartDashboard.putBoolean("left limit", LeftLimitActivated);
        SmartDashboard.putBoolean("right limit", RightLimitActivated);

        SmartDashboard.putBoolean("left zeroed", LeftZero);
        SmartDashboard.putBoolean("right zeroed", RightZero);

        if(LeftZero == false){
            if(!LeftLimitActivated){
                leftEncoder.setPosition(0);
                LeftZero = true;
            }
        }

        if(RightZero == false){
            if(!RightLimitActivated){
                rightEncoder.setPosition(0);
                RightZero = true;
            }
        }
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
            else if(LeftZero == false){
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
            if(!LeftLimitActivated){
                leftarm.set(0);
                System.out.println("left limit");
            }
            else{
                leftarm.set(1);
                System.out.println("down left");
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
            else if(RightZero == false){
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
            if(!RightLimitActivated){
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
