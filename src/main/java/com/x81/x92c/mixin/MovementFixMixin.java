package com.x81.x92c.mixin;

import com.x81.x92c.client.auto.movement.MovementManager;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class MovementFixMixin {
    @Shadow @Final private GameOptions settings;
    MovementManager mvm = MovementManager.getInstance();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(boolean bl, CallbackInfo ci) {
        if (mvm._switchFrame) {
            this.settings.keyForward.setPressed(mvm.keyWalk);
            this.settings.keyJump.setPressed(mvm.keyJump);
            this.settings.keySprint.setPressed(mvm.keySprint);

            mvm.onDidUpdate();
        }
    }
}
