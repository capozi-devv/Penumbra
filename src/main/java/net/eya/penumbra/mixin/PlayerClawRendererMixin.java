package net.eya.penumbra.mixin;

import net.eya.penumbra.common.item.DecadenceClawsItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerClawRendererMixin {

    private void renderItemInHand(
            PlayerEntityRenderer renderer,
            AbstractClientPlayerEntity player,
            ItemStack itemStack,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            boolean isLeftHand
    ) {
        matrices.push();

        PlayerEntityModel<AbstractClientPlayerEntity> model =
                ((LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) renderer)
                        .getModel();

        model.setAngles(player, 0.0F, 0.0F, player.age, player.headYaw, player.getPitch());

        float xOffset = isLeftHand ? -0.5F : 0.5F;  // tweak these values via testing
        matrices.translate(xOffset, 0.5F, 0.0D);

        ModelPart armModel = ((BipedEntityModelInvoker) model).invokeGetArm(isLeftHand ? Arm.LEFT : Arm.RIGHT);

        armModel.rotate(matrices);

        ModelTransformationMode transformMode = isLeftHand
                ? ModelTransformationMode.THIRD_PERSON_LEFT_HAND
                : ModelTransformationMode.THIRD_PERSON_RIGHT_HAND;

        HeldItemRenderer itemRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getHeldItemRenderer();
        itemRenderer.renderItem(
                player,
                itemStack,
                transformMode,
                isLeftHand,
                matrices,
                vertexConsumers,
                light
        );

        matrices.pop();
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderClawsOnPlayer(
            AbstractClientPlayerEntity player,
            float yaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        PlayerEntityRenderer self = (PlayerEntityRenderer) (Object) this;

        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();

        if (mainHand.getItem() instanceof DecadenceClawsItem) {
            renderItemInHand(self, player, mainHand, matrices, vertexConsumers, light, false); // Right hand
        }

        if (offHand.getItem() instanceof DecadenceClawsItem) {
            renderItemInHand(self, player, offHand, matrices, vertexConsumers, light, true); // Left hand
        }
    }
}