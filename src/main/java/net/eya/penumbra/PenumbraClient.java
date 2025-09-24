package net.eya.penumbra;

import net.eya.penumbra.common.command.SkyboxCommands;
import net.eya.penumbra.common.util.CustomSkyRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class PenumbraClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RegistryKey<DimensionType> dimensionKey = RegistryKey.of(
                RegistryKeys.DIMENSION_TYPE,
                new Identifier("minecraft", "overworld") // Or your custom dimension ID
        );

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            SkyboxCommands.register(dispatcher);
        });
    }
}
