package net.eya.penumbra.mixin;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.eya.penumbra.common.lodestone.particle.EclipseAvatarParticles;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void tickCustomTimer(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        EclipseAvatarComponent avatarComponent = Penumbra.getEclipseAvatar().get(player);
        avatarComponent.tickAvatarValue(player);
        if(avatarComponent.isAvatar(player)) {
            EclipseAvatarParticles.afterEffectParticles(player.getWorld(), player.getPos());
        }
    }
}
