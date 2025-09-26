package net.eya.penumbra.common.command;

import com.mojang.brigadier.CommandDispatcher;
import net.eya.penumbra.common.render.CustomSkyRenderer;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SkyboxCommands {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                literal("skybox")
                        .then(literal("set")
                                .executes(context -> {
                                    CustomSkyRenderer.enabled = true;
                                    MinecraftClient.getInstance().player.sendMessage(
                                            Text.literal("Custom skybox and sun applied."), false);
                                    return 1;
                                })
                        )
                        .then(literal("clear")
                                .executes(context -> {
                                    CustomSkyRenderer.enabled = false;
                                    MinecraftClient.getInstance().player.sendMessage(
                                            Text.literal("Skybox reset to default."), false);
                                    return 1;
                                })
                        )
        );
    }
}