package net.eya.penumbra.render;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.util.Identifier;

public class EclipseWingsFeatureRenderer
        extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private static final Identifier TEXTURE =
            new Identifier("penumbra", "textures/entity/eclipse_wings.png");

    private final EclipseWings<AbstractClientPlayerEntity> model;

    public EclipseWingsFeatureRenderer(PlayerEntityRenderer renderer, ModelPart root) {
        super(renderer);
        this.model = new EclipseWings<>(root);
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       AbstractClientPlayerEntity player,
                       float limbSwing, float limbSwingAmount,
                       float tickDelta, float ageInTicks,
                       float headYaw, float headPitch) {
        if (player.isInvisible()) return;

        matrices.push();

        // Align wings with the player's body rotation/position
        ((PlayerEntityModel<?>) this.getContextModel()).body.rotate(matrices);

        matrices.translate(0.0D, -0.08D, 0.12D);
        matrices.scale(1.5F, 1.5F, 1.5F);

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
        model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV,
                1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }
}
