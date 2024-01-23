package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

public class VisionSubsystem {
    //robot catches targets
    public double cameraX;
    public double cameraY;
    public boolean cameraTargets;

    //best target
    public double bestX;
    public double bestY;
    public boolean bestTargets;

    //all tags (example, for now)
    public double tag1x;
    public double tag1y;

    private PhotonCamera bestCamera = new PhotonCamera("name");
    private PhotonCamera Camera = new PhotonCamera("name");

    public void initialize(){
        bestCamera.setPipelineIndex(0);

        Camera.setPipelineIndex(1);
    }

    public void GetBestTarget(){
        var result = Camera.getLatestResult();
        cameraTargets = result.hasTargets();

        if(cameraTargets){
            PhotonTrackedTarget target = result.getBestTarget();
            cameraX = target.getYaw();
            cameraY = target.getPitch();
        }

        var bestResult = bestCamera.getLatestResult();
        bestTargets = bestResult.hasTargets();

        if(bestTargets){
            //listing the targets
            List<PhotonTrackedTarget> targets = result.getTargets();
        }
    }


}