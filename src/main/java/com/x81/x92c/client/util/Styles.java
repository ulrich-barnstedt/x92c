package com.x81.x92c.client.util;

import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

public class Styles {
    private static final boolean hideName = false;

    public static final Style prefixStyle = hideName ?
            new Style()
                    .setColor(Formatting.DARK_GREEN)
                    .setObfuscated(true) :
            new Style()
                    .setColor(Formatting.DARK_GREEN);
    public static final Style normalStyle = new Style()
            .setColor(Formatting.RESET);
    public static final Style errorStyle = new Style()
            .setBold(true)
            .setColor(Formatting.RED);
    public static final Style silentStyle = new Style()
            .setItalic(true)
            .setColor(Formatting.GRAY);
}
