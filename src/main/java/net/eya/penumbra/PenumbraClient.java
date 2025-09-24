package net.eya.penumbra;

import net.eya.penumbra.common.render.CustomPlayerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.eya.penumbra.common.render.EclipseWings;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.EntityType;

public class PenumbraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(EclipseWings.LAYER_LOCATION, EclipseWings::getTexturedModelData);
        EntityRendererRegistry.register(
                EntityType.PLAYER,
                (EntityRendererFactory)(EntityRendererFactory.Context ctx) -> new CustomPlayerRenderer(ctx, false)
        );
    }
}
