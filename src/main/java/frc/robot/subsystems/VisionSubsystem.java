package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
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

    private PhotonCamera bestCamera = new PhotonCamera("ShooterCamAprilTags");
    private PhotonCamera intakeCamera = new PhotonCamera("IntakeCam");

    @Override
    public void initialize(){
        intakeCamera.setPipelineIndex(0);
        bestCamera.setPipelineIndex(0);
        PortForwarder.add(5800, "photonvision.local", 5800);
    }

    public void periodic(){
        VisionUpdate();
        DisplayStats();
    }

    public void NoteUpdate(){
        var noteResult = intakeCamera.getLatestResult();
        notesPresent = noteResult.hasTargets();

        PhotonTrackedTarget bestTarget = noteResult.getBestTarget();
        noteX = bestTarget.getYaw();
        noteY = bestTarget.getPitch();

        if(noteY < 3){
            UserPolicy.noteHoming = false;
        }
    }

    public void VisionUpdate(){
        var result = bestCamera.getLatestResult();
        targetsPresent = result.hasTargets();

        if(cameraY < 3) {
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
                System.out.print(id);

                if(id == 7 && UserPolicy.speakerShoot){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    if(cameraY > 3) {
                        UserPolicy.closetospeaker = true;
                    }
                }

                if(id == 4 && UserPolicy.speakerShoot){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    if(cameraY > 3) {
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
    }
    //TODO: detect april tags with this code and be able to use this code for futher systems in the commands 
}