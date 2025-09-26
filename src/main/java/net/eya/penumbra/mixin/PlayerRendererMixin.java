package net.eya.penumbra.mixin;

import net.eya.penumbra.foundation.ItemInit;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin {
    private static final Identifier CUSTOM_SKIN = new Identifier("penumbra", "textures/entity/eclipse_skin.png");

    @Inject(method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"),
            cancellable = true)
    private void overrideSkin(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) {
        if (isWearingFullEclipseArmor(player)) {
            cir.setReturnValue(CUSTOM_SKIN);
        }
    }
    private boolean isWearingFullEclipseArmor(AbstractClientPlayerEntity player) {
        ItemStack helmet = player.getInventory().getArmorStack(3); // head
        ItemStack chestplate = player.getInventory().getArmorStack(2); // chest
        ItemStack leggings = player.getInventory().getArmorStack(1); // legs
        ItemStack boots = player.getInventory().getArmorStack(0); // feet
        return helmet.getItem() == ItemInit.ECLIPSE_HELMET &&
                chestplate.getItem() == ItemInit.ECLIPSE_CHESTPLATE &&
                leggings.getItem() == ItemInit.ECLIPSE_LEGGINGS &&
                boots.getItem() == ItemInit.ECLIPSE_BOOTS;
    }
}
