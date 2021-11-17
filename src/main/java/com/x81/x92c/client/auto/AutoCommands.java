package com.x81.x92c.client.auto;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.x81.x92c.client.util.IO;
import net.minecraft.server.command.ServerCommandSource;
import java.lang.reflect.Field;

import static net.minecraft.server.command.CommandManager.literal;

public class AutoCommands {
    private static final AlgorithmSettings settings = AlgorithmSettings.getInstance();

    static public LiteralArgumentBuilder<ServerCommandSource> registerAutoCommands () {
        String[] props = {
            "ignoreFovLimiter",
            "ignoreReachLimiter",
            "debugOverrideLock"
        };

        LiteralArgumentBuilder<ServerCommandSource> thisLiteral = literal("autoSettings");

        for (String prop : props) {
            thisLiteral = thisLiteral.then(literal(prop).executes(context -> {
                Class<?> c = settings.getClass();

                Field f = null;
                try {
                    f = c.getDeclaredField(prop);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                f.setAccessible(true);

                boolean setValue = false;
                try {
                    setValue = !(Boolean) f.get(settings);
                    f.set(settings, setValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                printPropString(prop, setValue);
                return 0;
            }));
        }

        thisLiteral = thisLiteral.then(literal("hypermode").executes(c -> {
            if (settings.isHypermode()) {
                printPropString("hypermode", false);
                settings.panic();
            } else {
                printPropString("hypermode", true);
                settings.hypermode();
            }

            return 0;
        }));

        return thisLiteral.executes(c -> {
            IO.chatError("Specify a setting");
            return 1;
        });

    }

    private static void printPropString (String prop, boolean value) {
        IO.chat(prop + " was " + IO.boolToEnabled(value));
    }
}
