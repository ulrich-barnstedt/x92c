package com.x81.x92c.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Mouse.class)
public interface MouseAccessor {
    @Accessor("x")
    public double getX();

    @Accessor("y")
    public double getY();

    @Accessor("leftButtonClicked")
    public void setLeftButtonClicked(boolean leftButtonClicked);

    @Invoker("onMouseButton")
    public void invokeOnMouseButton(long window, int button, int action, int mods);

    @Invoker("onCursorPos")
    public void invokeOnCursorPos(long window, double x, double y);
}

    /*
    @Accessor("x")
    public void setX(double x);

    @Accessor("y")
    public void setY(double y);


    @Accessor("cursorDeltaX")
    public double getCursorDeltaX();

    @Accessor("cursorDeltaX")
    public void setCursorDeltaX(double cursorDeltaX);*/