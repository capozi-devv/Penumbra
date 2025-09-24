package net.eya.penumbra;

import net.eya.penumbra.rendering.EclipseHorns;
import net.eya.penumbra.rendering.HornsFeatureRenderer;
import net.eya.penumbra.rendering.WingsFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.eya.penumbra.rendering.EclipseWings;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

public class PenumbraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(EclipseWings.LAYER_LOCATION, EclipseWings::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EclipseHorns.LAYER_LOCATION, EclipseHorns::getTexturedModelData);

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
    }
}
