package net.eya.penumbra.mixin;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void penumbra$useItem(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        EclipseAvatarComponent component = Penumbra.getEclipseAvatar().get(user);
        if(user.getStackInHand(hand).isFood() && component.isAvatar((ServerPlayerEntity)user)) {
            cir.cancel();
        }
    }
}
