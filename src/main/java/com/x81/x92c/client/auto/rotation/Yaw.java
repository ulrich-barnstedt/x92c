package com.x81.x92c.client.auto.rotation;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Yaw extends Rotation {
    private static Yaw INSTANCE;

    public static Yaw getInstance () {
        if (INSTANCE == null)
            INSTANCE = new Yaw();

        return INSTANCE;
    }

    //-----------

    private final double maxDegreeInaccuracy = 3;

    private double yawDistance = 0;
    private double yawTarget = 181;
    private double yawSource = 0;

    private Yaw () {
        HudRenderCallback.EVENT.register(e -> {
            if (yawTarget >= 181 || yawTarget <= -181) return;

            double playerYaw = getFromPlayer();
            if (Math.abs(yawTarget - playerYaw) < maxDegreeInaccuracy) {
                yawTarget = 181;
                return;
            }

            double factor = snap ? 2 : Rotation.sinFactor(180, yawSource, yawTarget, playerYaw);
            mouse.invokeOnCursorPos(
                    MinecraftClient.getInstance().getWindow().getHandle(),
                    mouse.getX() + factor * yawDistance,
                    mouse.getY()
            );
        });
    }

    public void to (double newValue) {
        yawTarget = newValue;
        yawSource = getFromPlayer();

        yawDistance = yawTarget - yawSource;
        yawDistance = (int) Math.round(Math.floor(Math.abs((yawDistance) % 360) / 180)) != 0 ?
                Math.signum(yawDistance) * - (180 - (Math.abs(yawDistance) % 180)) :
                ((yawDistance) % 360);
    }

    public double getFromPlayer () {
        Vec3d rot = client.cameraEntity.getRotationVector();
        return ((540 + 180 * ((Math.atan2(rot.getZ(), rot.getX()) / Math.PI) - 0.5)) % 360) - 180;
    }
}

/*private double plugSin () {
        return Math.abs(Math.sin(((yawTarget - yawSource) - (yawTarget - getFromPlayer()) + 180) / (yawTarget - yawSource + 180) * Math.PI));
    }*/
