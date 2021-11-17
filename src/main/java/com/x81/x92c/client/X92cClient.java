package com.x81.x92c.client;

import com.x81.x92c.client.util.Utils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class X92cClient implements ClientModInitializer {
    Utils utils = new Utils();

    @Override
    public void onInitializeClient() {
        this.utils.registerCommands();
        this.utils.registerKeybinds();

        this.utils.misc();
    }
}
