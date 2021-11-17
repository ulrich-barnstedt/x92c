package com.x81.x92c.client.auto.rotation;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class Pitch extends Rotation {
    private static Pitch INSTANCE;

    public static Pitch getInstance () {
        if (INSTANCE == null)
            INSTANCE = new Pitch();

        return INSTANCE;
    }

    //-----------

    private double pitchDistance = 0;
    private double pitchTarget = 91;
    private double pitchSource = 0;

    private final double maxDegreeInaccuracy = 2;

    private Pitch () {
        HudRenderCallback.EVENT.register(e -> {
            if (pitchTarget >= 91 || pitchTarget <= -91) return;

            double playerPitch = getFromPlayer();
            if (Math.abs(pitchTarget - playerPitch) < maxDegreeInaccuracy) {
                pitchTarget = 91;
                return;
            }

            double factor = snap ? 1 : Rotation.sinFactor(90, pitchSource, pitchTarget, playerPitch);
            mouse.invokeOnCursorPos(
                    MinecraftClient.getInstance().getWindow().getHandle(),
                    mouse.getX(),
                    mouse.getY() + factor * pitchDistance
            );
        });
    }

    public void to (double newValue) {
        pitchTarget = newValue;
        pitchSource = getFromPlayer();

        pitchDistance = pitchTarget - pitchSource;
    }

    public double getFromPlayer () {
        return client.player.getPitch(0);
    }
}

//return client.cameraEntity.getRotationVector().getY() * -90;

           // IO.chat((client.player.getYaw(0) % 360 + 180) + "");

/*private final MinecraftClient client = MinecraftClient.getInstance();
    private static final MouseAccessor mouse = (MouseAccessor) client.mouse;

    private boolean hasInit = false;

    public void to (double newValue)  {
        if (!hasInit) init();


    }

    private void init () {
        hasInit = true;

        HudRenderCallback.EVENT.register(e -> {

        });
    }

    static double getFromPlayer () {
        Vec3d rot = client.cameraEntity.getRotationVector();
        return 0;
    }*/
