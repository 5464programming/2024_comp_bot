package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.RobotStatus;
import frc.robot.OI.UserPolicy;

public class ShooterSubsystem extends EntechSubsystem {

    //TODO: relate the RPMs of the shooter to the distance from the target using AprilTags


    CANSparkMax shootTop = new CANSparkMax(5, MotorType.kBrushless);
    CANSparkMax shootBottom = new CANSparkMax(6, MotorType.kBrushless);
    public RelativeEncoder codeTop = shootTop.getEncoder();
    public RelativeEncoder codeBottom = shootBottom.getEncoder();
    
    SparkPIDController PIDTop = shootTop.getPIDController();
    SparkPIDController PIDBottom = shootBottom.getPIDController();  
    
    double kP_top, kI_top, kD_top, kIz_top, kFF_top, kMaxOutput_top, kMinOutput_top, maxRPM_top;
    double kP_bottom, kI_bottom, kD_bottom, kIz_bottom, kFF_bottom, kMaxOutput_bottom, kMinOutput_bottom, maxRPM_bottom;

    public double SPtopSpeaker;
    public double SPbottomSpeaker;

    public double SPtopAmp;
    public double SPbottomAmp;

    public double FullSpeedAmpBottom;
    public double FullSpeedAmpTop;

    public double FullSpeedSpeakerTop;
    public double FullSpeedSpeakerBottom;

    public double TopPostRPM = 0;
    public double TopSpeakerRPM = 0;

    public double BottomPostRPM = 0;
    public double BottomSpeakerRPM = 0;

    public double PostPitch = 0;
    public double SpeakerPitch = 0;

    public double TopSlope;
    public double BottomSlope;

    public double TopRPMselect = 0;
    public double BottomRPMselect = 0;

    private static final boolean ENABLED = true;

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    @Override
    public void initialize(){

        TopSlope = (TopPostRPM-TopSpeakerRPM)/(PostPitch-SpeakerPitch);
        BottomSlope = (BottomPostRPM-BottomSpeakerRPM)/(PostPitch-SpeakerPitch);
                
        SPtopAmp = 200;
        SPbottomAmp = 2250;

        SPtopSpeaker = 3100;
        SPbottomSpeaker = 3200;

        SmartDashboard.putNumber("top speaker", SPtopSpeaker);
        SmartDashboard.putNumber("bottom speaker", SPbottomSpeaker);
        SmartDashboard.putNumber("top amp", SPtopAmp);
        SmartDashboard.putNumber("bottom amp", SPbottomAmp);

        kP_bottom = 0.00012;
        kI_bottom = 0.00000001;
        kD_bottom = 0.0;
        kIz_bottom = 0;
        kFF_bottom = 0.00018;
        kMaxOutput_bottom = 1;
        kMinOutput_bottom = -1;
        maxRPM_bottom = 5700;

        kP_top = 0.00012;
        kI_top = 0.00000001;
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
        shootTop.setIdleMode(IdleMode.kBrake);
    }

    public void Homing(double SPtop, double SPbottom){
        PIDTop.setReference(SPtop, CANSparkMax.ControlType.kVelocity);
        PIDBottom.setReference(SPbottom, CANSparkMax.ControlType.kVelocity);
    }

    public void periodic(){
        SmartDashboard.putBoolean("up to speed!", UserPolicy.shootUptoSpeed);

        SmartDashboard.putNumber("top encoder", codeTop.getVelocity());
        SmartDashboard.putNumber("bottom encoder", codeBottom.getVelocity());

        double kSPtopSpeaker = SmartDashboard.getNumber("top speaker", SPtopSpeaker);
        double kSPbottomSpeaker = SmartDashboard.getNumber("bottom speaker", SPbottomSpeaker);
        double kSPtopAmp = SmartDashboard.getNumber("top amp", SPtopAmp);
        double kSPbottomAmp = SmartDashboard.getNumber("bottom amp", SPbottomAmp);

        TopRPMselect = TopSlope*RobotStatus.AprilTagY + TopSpeakerRPM;
        BottomRPMselect = BottomSlope*RobotStatus.AprilTagY + BottomSpeakerRPM;

        if(SPtopSpeaker != kSPtopSpeaker){
            SPtopSpeaker = kSPtopSpeaker;
        }

        if(SPbottomSpeaker != kSPbottomSpeaker){
            SPbottomSpeaker = kSPbottomSpeaker;
        }
        
        if(SPtopAmp != kSPtopAmp){
            SPtopAmp = kSPtopAmp;
        }
        
        if(SPbottomAmp != kSPbottomAmp){
            SPbottomAmp = kSPbottomAmp;
        }
    }

    public void DisableShoot(){
        shootTop.set(0);
        shootBottom.set(0);
    }

    public void AmpCommand() {
        FullSpeedAmpBottom = codeBottom.getVelocity();        
        if(UserPolicy.ampShoot){
            Homing(SPtopAmp, SPbottomAmp);

        if(FullSpeedAmpBottom > FullSpeedAmpBottom-50 && FullSpeedAmpTop > FullSpeedAmpBottom-50){
            UserPolicy.shootUptoSpeed = true;
        }

        }
        else{
            DisableShoot();
        }
        }    

    public void SpeakerCommand(){
        FullSpeedSpeakerTop = codeTop.getVelocity();
        FullSpeedSpeakerBottom = codeBottom.getVelocity();
        
        if(UserPolicy.speakerShoot){
            Homing(TopRPMselect, BottomRPMselect);

        if(FullSpeedSpeakerTop > SPtopSpeaker-200 && FullSpeedSpeakerBottom > SPbottomSpeaker-200){
            UserPolicy.shootUptoSpeed = true;
        }
        
        }
        else{
            DisableShoot();
        }
    }

    public void ShootReverse(){
        if(UserPolicy.shootReverse){
            shootTop.set(-0.5);
            shootBottom.set(0.5);
        }
    }
}