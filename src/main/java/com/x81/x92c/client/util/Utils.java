package com.x81.x92c.client.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.x81.x92c.client.auto.Algorithm;
import com.x81.x92c.client.auto.AutoCommands;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.options.StickyKeyBinding;
import org.lwjgl.glfw.GLFW;

import static net.minecraft.server.command.CommandManager.literal;

public class Utils {
    MinecraftClient MC = MinecraftClient.getInstance();
    Algorithm alg = new Algorithm();

    public void registerCommands () {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(
                    literal("x92c")
                            .then(literal("maxTx").executes(c -> {
                                IO.chat(String.valueOf(RenderSystem.maxSupportedTextureSize()));
                                return 0;
                            }))
                            .then(literal("class").executes(c -> {
                                IO.chat(this.MC.toString());
                                return 0;
                            }))
                            .then(literal("info").executes(c -> {
                                IO.chat(String.format("%s on %s, 64bit: %s, Singleplayer: %s, Session %s, %s Players (World-data: %s), Lang %s",
                                        this.MC.getName(),
                                        this.MC.getGameVersion(),
                                        this.MC.is64Bit(),
                                        this.MC.isInSingleplayer(),
                                        this.MC.getGame().getCurrentSession().getSessionId(),
                                        this.MC.getGame().getCurrentSession().getPlayerCount(),
                                        this.MC.player.world.getPlayers().toArray().length,
                                        this.MC.getGame().getSelectedLanguage()
                                ));
                                return 0;
                            }))
                            .then(AutoCommands.registerAutoCommands())
                            .executes(c -> {
                                IO.chatError("No args passed.");
                                return 0;
                            })
            );
        });
    }

    public void registerKeybinds () {
        KeyBinding act = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding("ky.x92c.act", GLFW.GLFW_KEY_P, "ky.x92c.binds", () -> true));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (act.wasPressed()) {
                alg.toggle(client);
            }

            if (!act.isPressed()) return;
            alg.frame(client);
        });
    }

    public void misc () {
        //MC.player.setCustomName(new LiteralText("ur mom giey"));
    }
}


//client.player.move(MovementType.PLAYER, new Vec3d(1, 0, 0));
//client.player.getBrain().
//this.chat(client.player.setNoGravity(true));
//client.player.move(MovementType.PLAYER, client.cameraEntity.getRotationVector().multiply(3));

                /*assert client.player != null;

                if (Objects.isNull(client.player.getAttacker())) {
                    return;
                }
                client.player.getAttacker().damage(DamageSource.GENERIC, 1);*/

//client.player.attack(client.player.getAttacker());

/*HudRenderCallback.EVENT.register(e -> {
                    TextRenderer renderer = this.MC.textRenderer;
                    renderer.drawWithShadow(this.MC.fpsDebugString, 10, 10, 0xffffff);
                });*/
//this.MC.inGameHud.drawString(this.MC.textRenderer, "test", 1, 1, 100);

//this.MC.gameRenderer.disableShader();
//this.chat("Disabling shaders.");
//this.MC.inGameHud.drawString(this.MC.getFontManager().getTextRenderer(new Identifier("text")), this.MC.toString(), 0, 0, 0);

//client.player.sendMessage