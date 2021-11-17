package com.x81.x92c.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class IO {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    private static void chatInner (LiteralText text) {
        LiteralText prefix = new LiteralText("[x92c] ");
        prefix.setStyle(Styles.prefixStyle);

        IO.MC.inGameHud.addChatMessage(MessageType.SYSTEM, prefix.append(text));
    }

    public static void chat (String text) {
        LiteralText content = new LiteralText(text);
        content.setStyle(Styles.normalStyle);

        IO.chatInner(content);
    }

    public static void chatSilenced (String text) {
        LiteralText content = new LiteralText("[>>] " + text);
        content.setStyle(Styles.silentStyle);

        IO.chatInner(content);
    }

    public static void chatError (String text) {
        LiteralText content = new LiteralText("[ERR] " + text);
        content.setStyle(Styles.errorStyle);

        IO.chatInner(content);
    }

    public static String boolToEnabled (boolean input) {
        return input ? "enabled" : "disabled";
    }
}
