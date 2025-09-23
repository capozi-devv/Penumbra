package net.eya.penumbra.render;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.model.ModelPart;

public class CustomPlayerRenderer extends PlayerEntityRenderer {
    public CustomPlayerRenderer(EntityRendererFactory.Context ctx, boolean slim) {
        super(ctx, slim);

        // Use ctx.getPart(...) to get the baked ModelPart for our layer
        ModelPart wingsRoot = ctx.getPart(EclipseWings.LAYER_LOCATION);

        // addFeature is protected and works here because we are inside a subclass
        this.addFeature(new EclipseWingsFeatureRenderer(this, wingsRoot));
    }
}
