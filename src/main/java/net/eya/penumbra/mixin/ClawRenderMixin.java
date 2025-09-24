package net.eya.penumbra.mixin;

import net.eya.penumbra.common.item.DecadanceClawsItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class ClawRenderMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
    private void renderClawInEmptyHand(
            AbstractClientPlayerEntity player,
            float tickDelta,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack itemStack,
            float equipProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        HeldItemRenderer renderer = (HeldItemRenderer)(Object) this;

        if (itemStack.isEmpty()) {
            Hand otherHand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
            ItemStack otherStack = player.getStackInHand(otherHand);

            if (otherStack.getItem() instanceof DecadanceClawsItem) {
                boolean leftHanded = (hand == Hand.OFF_HAND);
                Arm arm = (hand == Hand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();

                matrices.push();

                int direction = arm == Arm.RIGHT ? 1 : -1;
                matrices.translate(direction * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F);

                float swingSin = MathHelper.sin(swingProgress * swingProgress * (float) Math.PI);
                float swingSqrt = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float) Math.PI);
                matrices.translate(direction * -0.4F * swingSin, 0.2F * swingSin, -0.2F * swingSqrt);

                renderer.renderItem(
                        player,
                        otherStack,
                        leftHanded ? ModelTransformationMode.FIRST_PERSON_LEFT_HAND : ModelTransformationMode.FIRST_PERSON_RIGHT_HAND,
                        leftHanded,
                        matrices,
                        vertexConsumers,
                        light
                );

                matrices.pop();
                ci.cancel();
            }
        }
    }
}