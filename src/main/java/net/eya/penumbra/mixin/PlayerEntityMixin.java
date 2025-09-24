package net.eya.penumbra.mixin;

import net.eya.penumbra.common.item.DecadenceClawsItem;
import net.eya.penumbra.common.lodestone.particle.ClawParticles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if((((Object)this) instanceof PlayerEntity)) {
            for (int i = 0; i < 21; i++) {
                int finalI = i;
                if(((PlayerEntity)((Object)this)).getServer() != null) {
                    if(((PlayerEntity)((Object)this)).getServer().getTicks() % (2 + finalI) == 0) {
                        if(DecadenceClawsItem.isDashing) {
                            ClawParticles.spawnClawParticles(((PlayerEntity)((Object)this)).getWorld(), ((PlayerEntity)((Object)this)).getPos());
                        }
                        if(i >= 20) {
                            DecadenceClawsItem.isDashing = false;
                        }
                    }
                } else if(MinecraftClient.getInstance().world.getTime() % (1 + finalI) == 0) {
                    if(DecadenceClawsItem.isDashing) {
                        ClawParticles.spawnClawParticles(((PlayerEntity)((Object)this)).getWorld(), ((PlayerEntity)((Object)this)).getPos());
                    }
                    if(i >= 20) {
                        DecadenceClawsItem.isDashing = false;
                    }
                }
            }
        }
    }
}
