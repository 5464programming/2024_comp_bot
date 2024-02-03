package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class ShooterSubsystem extends EntechSubsystem {
    CANSparkMax shootTop = new CANSparkMax(6, MotorType.kBrushless);
    CANSparkMax shootBottom = new CANSparkMax(5, MotorType.kBrushless);
    RelativeEncoder codeTop = shootTop.getEncoder();
    RelativeEncoder codeBottom = shootBottom.getEncoder();

    SparkPIDController PIDTop = shootBottom.getPIDController();
    SparkPIDController PIDBottom = shootTop.getPIDController();  
    
    double kP_top, kI_top, kD_top, kIz_top, kFF_top, kMaxOutput_top, kMinOutput_top, maxRPM_top;
    double kP_bottom, kI_bottom, kD_bottom, kIz_bottom, kFF_bottom, kMaxOutput_bottom, kMinOutput_bottom, maxRPM_bottom;

    public double SPtop;
    public double SPbottom;

    private static final boolean ENABLED = true;

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    @Override
    public void initialize(){
        kP_bottom = 0.00006;
        kI_bottom = 0.0;
        kD_bottom = 0.0;
        kIz_bottom = 0;
        kFF_bottom = 0.00018;
        kMaxOutput_bottom = 1;
        kMinOutput_bottom = -1;
        maxRPM_bottom = 5700;

        kP_top = 0.00006;
        kI_top = 0.0;
        kD_top = 0.0;
        kIz_top = 0;
        kFF_top = 0.00018;
        kMaxOutput_top = 1;
        kMinOutput_top = -1;
        maxRPM_top = 5700;

        PIDBottom.setP(kP_bottom);
        PIDBottom.setI(kI_bottom);
        PIDBottom.setD(kD_bottom);
        PIDBottom.setIZone(kIz_bottom);
        PIDBottom.setFF(kFF_bottom);
        PIDBottom.setOutputRange(kMinOutput_bottom, kMaxOutput_bottom);

        PIDTop.setP(kP_top);
        PIDTop.setI(kI_top);
        PIDTop.setD(kD_top);
        PIDTop.setIZone(kIz_top);
        PIDTop.setFF(kFF_top);
        PIDTop.setOutputRange(kMinOutput_top, kMaxOutput_top);
    }

    public void Homing(){
        PIDTop.setReference(SPtop, CANSparkMax.ControlType.kVelocity);
        PIDBottom.setReference(SPbottom, CANSparkMax.ControlType.kVelocity);
    }

    public void DisplayEncoders(){
        System.out.println("PID");
        SmartDashboard.putNumber("top encoder", codeTop.getVelocity());
        SmartDashboard.putNumber("bottom encoder", codeBottom.getVelocity());
    }

    public void DisableShoot(){
        SPtop = 0;
        SPbottom = 0;
        shootTop.set(0);
        shootBottom.set(0);
    }

    public void AmpCommand() {
        if(UserPolicy.ampShoot){
            SPtop = -300;
            SPbottom = -300;
            shootTop.set(0.6);
            shootBottom.set(-0.6);
            //Homing();
            DisplayEncoders();
        }
        else{
            DisableShoot();
        }
        }    

    public void SpeakerCommand(){
        if(UserPolicy.speakerShoot){
            SPtop = -300;
            SPbottom = -300;
            shootTop.set(0.6);
            shootBottom.set(-0.6);
            // Homing();
            DisplayEncoders();
        }
        else{
            DisableShoot();
        }
    }

}