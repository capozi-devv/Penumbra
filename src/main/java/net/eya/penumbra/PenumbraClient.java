package net.eya.penumbra;

import net.eya.penumbra.common.command.SkyboxCommands;
import net.eya.penumbra.common.lodestone.worldvfx.AllVFX;
import net.eya.penumbra.common.render.EclipseHorns;
import net.eya.penumbra.common.render.EclipseWings;
import net.eya.penumbra.common.render.HornsFeatureRenderer;
import net.eya.penumbra.common.render.WingsFeatureRenderer;
import net.eya.penumbra.foundation.BlockInit;
import net.eya.penumbra.foundation.ItemInit;
import net.eya.penumbra.foundation.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class PenumbraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(EclipseWings.LAYER_LOCATION, EclipseWings::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EclipseHorns.LAYER_LOCATION, EclipseHorns::getTexturedModelData);
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.OBELISK, RenderLayer.getCutout());
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, renderer, registrationHelper, context) -> {
            if (renderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(
                        new WingsFeatureRenderer(
                                (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) renderer, // 👈 cast it here
                                context.getModelLoader().getModelPart(EclipseWings.LAYER_LOCATION),
                                0,0,0
                        )
                );
                registrationHelper.register(
                        new HornsFeatureRenderer(
                                (FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>>) renderer, // 👈 cast it here
                                context.getModelLoader().getModelPart(EclipseHorns.LAYER_LOCATION),
                                0,-0.1F,0
                        )
                );
            }
        });
        RegistryKey<DimensionType> dimensionKey = RegistryKey.of(
                RegistryKeys.DIMENSION_TYPE,
                new Identifier("minecraft", "overworld") // Or your custom dimension ID
        );
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            SkyboxCommands.register(dispatcher);
        });
        ParticleInit.registerParticleFactory();
        ModelPredicateProviderRegistry.register(
                ItemInit.WARHORN,
                new Identifier("tooting"),
                (stack, world, entity, seed) -> {
                    if (entity == null) return 0.0F;
                    if (entity.isUsingItem() && entity.getActiveItem() == stack) {
                        return 1.0F;
                    }
                    return 0.0F;
                }
        );
        WorldRenderEvents.LAST.register(ctx -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null) return;

            AllVFX.obeliskBeam(new BlockPos(0, 80, 0), 20);
        });
    }
}
