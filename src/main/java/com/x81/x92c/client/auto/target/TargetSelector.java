package com.x81.x92c.client.auto.target;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import com.x81.x92c.client.util.IO;

public class TargetSelector {
    private int calculationTick = 3;
    PlayerEntity cachedTarget;
    final private float reach = 4.5f;

    public PlayerEntity calculateTarget(ClientPlayerEntity self) {
        if (++calculationTick != 4) return cachedTarget;
        calculationTick = 0;

        PlayerEntity closest = null;
        float closestDistance = 900;
        double smallestYawDiff = 180;

        for (PlayerEntity target : self.world.getPlayers()) {
            if (target == self) continue;

            float distanceToTarget = self.distanceTo(target);
            if (distanceToTarget < closestDistance && distanceToTarget > reach) closest = target;
            if (distanceToTarget > reach) continue;

            PlayerYawDiff yd = new PlayerYawDiff(self, target);
            if (yd.angleDiff() < smallestYawDiff) {
                smallestYawDiff = yd.angleDiff();
                closest = target;
            }
        }

        cachedTarget = closest;
        return closest;
    }
}


        /*PlayerEntity closest = null;
        float closestDistance = 900;
        double smallestYawDiff = 180;

        for (PlayerEntity player : self.world.getPlayers()) {
            if (player == self) continue;
            float distanceTo = player.distanceTo(self);
            if (distanceTo < closestDistance && distanceTo > reach) closest = player;
            if (distanceTo > reach) continue;

            PlayerDistDiff diff = new PlayerDistDiff(self, player);

            if (diff.angleDiff() <= smallestYawDiff) {
                smallestYawDiff = diff.angleDiff();
                closest = player;
            }
        }

        cachedTarget = closest;
        return closest;*/

//IO.chat(diff.angleDiff() + "");