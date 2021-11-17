package com.x81.x92c.client.auto.target;

import com.x81.x92c.client.auto.rotation.RotationManager;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerYawDiff {
    double x;
    double y;
    double z;

    public PlayerYawDiff(PlayerEntity self, PlayerEntity other) {
        this.x = other.getX() - self.getX();
        this.y = other.getY() - self.getY();
        this.z = other.getZ() - self.getZ();
    }

    public double angle () {
        return ((540 + 180 * ((Math.atan2(this.z, this.x) / Math.PI) - 0.5)) % 360) - 180;
    }

    public double angleDiff() {
        double dist = this.angle() - RotationManager.yaw.getFromPlayer();
        return (int) Math.round(Math.floor(Math.abs((dist) % 360) / 180)) != 0 ?
                (180 - (Math.abs(dist) % 180)) :
                Math.abs((dist) % 360);
    }
}

        /*
        yawDistance = yawTarget - yawSource;
        yawDistance = (int) Math.round(Math.floor(Math.abs((yawDistance) % 360) / 180)) != 0 ?
                (180 - (Math.abs(yawDistance) % 180)) :
                Math.abs((yawDistance) % 360));
         */
//return Math.abs((this.angle() + 180) - (RotationManager.yaw.getFromPlayer() + 180));
