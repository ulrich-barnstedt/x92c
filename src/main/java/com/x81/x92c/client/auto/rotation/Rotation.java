package com.x81.x92c.client.auto.rotation;

import com.x81.x92c.mixin.MouseAccessor;
import net.minecraft.client.MinecraftClient;

abstract class Rotation {
    protected final MinecraftClient client = MinecraftClient.getInstance();
    protected final MouseAccessor mouse = (MouseAccessor) client.mouse;
    protected boolean snap = false;

    abstract public void to (double newValue);
    abstract double getFromPlayer ();

    public void setSnap(boolean to) {
        snap = to;
    }

    protected static double sinFactor (double total, double source, double target, double playerAt) {
        if (target - source < 10) return 0.5;

        return Math.abs(Math.sin(
            ((target - source) - (target - playerAt) + total) / (target - source + total) * Math.PI
        ));
    }
}
