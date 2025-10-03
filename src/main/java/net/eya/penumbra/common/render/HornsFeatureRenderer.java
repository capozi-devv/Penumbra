package net.eya.penumbra.common.render;

import net.eya.penumbra.foundation.ItemInit;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;

public class HornsFeatureRenderer extends FeatureRenderer<PlayerEntity, PlayerEntityModel<PlayerEntity>> {
    private static final Identifier TEXTURE = new Identifier("penumbra", "textures/entity/eclipse_horns.png");
    private final EclipseHorns model;

    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;

    public HornsFeatureRenderer(
            FeatureRendererContext<PlayerEntity, PlayerEntityModel<PlayerEntity>> context,
            ModelPart modelPart,
            float offsetX,
            float offsetY,
            float offsetZ
    ) {
        super(context);
        this.model = new EclipseHorns(modelPart);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       PlayerEntity player, float limbSwing, float limbSwingAmount, float tickDelta,
                       float ageInTicks, float netHeadYaw, float headPitch) {

        // ✅ Only render if the player is wearing full Eclipse armor
        if (!isWearingFullEclipseArmor(player)) return;

        matrices.push();

        ((PlayerEntityModel<?>) this.getContextModel()).head.rotate(matrices);

        matrices.translate(offsetX, offsetY, offsetZ);

        // Render the horns
        this.model.setAngles(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.model.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(TEXTURE)),
                light,
                OverlayTexture.DEFAULT_UV,
                1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }

    private boolean isWearingFullEclipseArmor(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ItemInit.ECLIPSE_HELMET &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == ItemInit.ECLIPSE_CHESTPLATE &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == ItemInit.ECLIPSE_LEGGINGS &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == ItemInit.ECLIPSE_BOOTS;
    }
}
