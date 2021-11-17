package com.x81.x92c.client.auto;

import com.x81.x92c.client.auto.movement.MovementManager;
import com.x81.x92c.client.auto.rotation.RotationManager;
import com.x81.x92c.client.auto.target.PlayerYawDiff;
import com.x81.x92c.client.auto.target.TargetSelector;
import com.x81.x92c.mixin.MouseAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import java.util.Objects;
import com.x81.x92c.client.util.IO;

public class Algorithm {
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    private final MovementManager mvm = MovementManager.getInstance();
    private final TargetSelector selector = new TargetSelector();
    private final AlgorithmSettings settings = AlgorithmSettings.getInstance();
    private MouseAccessor mouse = null;
    private boolean active = false;
    private final double reach = 4.5;
    private boolean hitTick = false;

    public void toggle(MinecraftClient client) {
        if (!active && client.world.getPlayers().toArray().length == 1 && !settings.debugOverrideLock) {  //active too
            IO.chatError("Cannot be used in singleplayer / no players found.");
            return;
        }
        active = !active;

        if (mouse == null) {
            mouse = (MouseAccessor) MC.mouse;
        }

        if (active) {
            IO.chatSilenced("Enabled x92c.alg");
        } else {
            IO.chatSilenced("Disabled x92c.alg");
        }

        if (active) {
            if (settings.ignoreFovLimiter) {
                RotationManager.pitch.setSnap(true);
                RotationManager.yaw.setSnap(true);
            } else {
                RotationManager.pitch.setSnap(false);
                RotationManager.yaw.setSnap(false);
            }

            //Permanent sprint while enabled
            mvm.keySprint = true;
            mvm.updateOnNextTick();
        }

        //Disable any remaining keys
        if (!active) {
            mvm.none();
            mvm.updateOnNextTick();
        }
    }

    public void frame(MinecraftClient client) {
        if (!active) return;
        hitTick = !hitTick;

        //IO.chat("Is active");

        ClientPlayerEntity player = client.player;
        PlayerEntity target = selector.calculateTarget(player);
        if (Objects.isNull(target)) {
            IO.chatSilenced("No target.");
            return;
        }

        float distanceToTarget = player.distanceTo(target);

        //rotation
        PlayerYawDiff dist = new PlayerYawDiff(player, target);
        RotationManager.yaw.to(dist.angle());
        RotationManager.pitch.to(
    (-180) *
            Math.atan2(
                target.getY() - player.getY(),
                Math.sqrt(
                    Math.pow(target.getX() - player.getX(), 2) + Math.pow(target.getZ() - player.getZ(), 2)
                )
            ) / Math.PI);

        //movement
        if (distanceToTarget > 7) {
            mvm.keyWalk = true;
            mvm.keyJump = true;

            mvm.updateOnNextTick();
        } else if (distanceToTarget > 3) {
            mvm.keyWalk = true;

            mvm.updateOnNextTick();
        } else if (distanceToTarget < 2) {
            mvm.keyWalk = false;

            mvm.updateOnNextTick();
        } else if (mvm.keyJump && mvm.keyWalk) {
            mvm.keyJump = false;
            mvm.keyWalk = false;

            mvm.updateOnNextTick();
        }

        //attacking
        if ((distanceToTarget < reach || settings.ignoreReachLimiter) && hitTick) {
            //mouse.setLeftButtonClicked(true);
            mouse.invokeOnMouseButton(MC.getWindow().getHandle(), 0, 1, 0);
        } else {
            mouse.invokeOnMouseButton(MC.getWindow().getHandle(), 0, 0, 0);
        }
    }
}

//IO.chat(dist.angle() + "");
//IO.chat("found");

//RotationManager.pitch.to(Math.atan((target.getY() - player.getY()) / distanceToTarget));
//RotationManager.pitch.to(Math.atan2(target.getY() - player.getY(), Math.sqrt(Math.pow(target.getX() - player.getX(), 2) + Math.pow(target.getZ() - player.getZ(), 2))));


/*player.setSprinting(true);
        player.jump();
        player.travel(new Vec3d(0,0, player.getMovementSpeed()));*/