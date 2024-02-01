package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

            System.out.print("bestTags:");
            
            for(int i = 0; i < targets.size(); i++){
                int id = targets.get(i).getFiducialId();
                System.out.print(i);

                if(id == 1){
                    tag1x = targets.get(i).getYaw();
                    tag1y = targets.get(i).getPitch();
                }
            }
            PhotonTrackedTarget bestTarget = bestResult.getBestTarget();
            bestX = bestTarget.getYaw();
            bestY = bestTarget.getPitch();
        }
    }
    public void setPipelineIndex(int pipelineIndex){
        Camera.setPipelineIndex(pipelineIndex);
    }
    public void DisplayStats(){
        SmartDashboard.putBoolean("Camera Target Detection", cameraTargets);
        SmartDashboard.putNumber("Camera X", cameraX);
        SmartDashboard.putNumber("Camera Y", cameraY);
    }
    //TODO: detect april tags with this code and be able to use this code for futher systems in the commands 
}