package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.RobotStatus;
import frc.robot.OI.UserPolicy;

public class VisionSubsystem extends EntechSubsystem{
    private static final boolean ENABLED = true;

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    //robot catches targets
    public double cameraX;
    public double cameraY;
    public double aprilTagSkew;

    //best target
    public double bestX;
    public double bestY;
    public boolean targetsPresent;

    //all tags (example, for now)
    public double tag8x;
    public double tag8y;

    public double note;
    public boolean notesPresent;
    public double noteX;
    public double noteY;

    private PhotonCamera AprilTagCamera = new PhotonCamera("AprilTagCamera");
    private PhotonCamera NoteCamera = new PhotonCamera("NoteCamera");

    public double yValue = 13;

    @Override
    public void initialize(){
        NoteCamera.setPipelineIndex(0);
        AprilTagCamera.setPipelineIndex(0);
        PortForwarder.add(5800, "photonvision.local", 5800);
    }

    public void periodic(){
        NoteUpdate();
        VisionUpdate();
        DisplayStats();
    }

    public void NoteUpdate(){
        var noteResult = NoteCamera.getLatestResult();
        notesPresent = noteResult.hasTargets();

        if(notesPresent){
            PhotonTrackedTarget bestTarget = noteResult.getBestTarget();
            noteX = bestTarget.getYaw();
            noteY = bestTarget.getPitch();
            RobotStatus.noteIsDetected = true;
            RobotStatus.noteVisionX = noteX;
            RobotStatus.noteVisionY = noteY;
        }
        else{
            RobotStatus.noteIsDetected = false;
        }
    }

    public void VisionUpdate(){
        var result = AprilTagCamera.getLatestResult();
        targetsPresent = result.hasTargets();

        if(cameraY < 13) {
            UserPolicy.closetospeaker = false;
        }

        if(targetsPresent){
            UserPolicy.aprilTagsAreDetected = true;
            //listing the targets
            List<PhotonTrackedTarget> targets = result.getTargets();

            //looking in the grocery store
            for(int i = 0; i < targets.size(); i++){
                //check grocery aisle i
                int id = targets.get(i).getFiducialId();

                if(id == 8 && UserPolicy.speakerShoot){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    aprilTagSkew = targets.get(i).getSkew();
                    if(cameraY > yValue) {
                        UserPolicy.closetospeaker = true;
                    }
                }

                if(id == 4 && UserPolicy.speakerShoot){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    if(cameraY > yValue) {
                        UserPolicy.closetospeaker = true;
                    }
                }

                // TODO: fix this user policy check to be something else....... ????
                if (id == 6 && UserPolicy.ampShoot) {
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();                
                }
            }
            
            PhotonTrackedTarget bestTarget = result.getBestTarget();
            bestX = bestTarget.getYaw();
            bestY = bestTarget.getPitch();
        }
        else{
            UserPolicy.aprilTagsAreDetected = false;
        }
    }
    public void DisplayStats(){
        SmartDashboard.putBoolean("Camera Target Detection", targetsPresent);
        SmartDashboard.putNumber("Camera X", cameraX);
        SmartDashboard.putNumber("Camera Y", cameraY);
        SmartDashboard.putBoolean("speakerclose", UserPolicy.closetospeaker);
        SmartDashboard.putNumber("AprilTagSkew", aprilTagSkew);
    }
    //TODO: detect april tags with this code and be able to use this code for futher systems in the commands 
}