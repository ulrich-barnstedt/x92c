package com.x81.x92c.mixin;

import com.x81.x92c.client.Bugfix.Bugfix;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMovementFixMixin {
    @Inject(method = "onCursorPos", at = @At("HEAD"))
    void x(long window, double x, double y, CallbackInfo ci) {
        Bugfix.consume(x, y);
    }
}
