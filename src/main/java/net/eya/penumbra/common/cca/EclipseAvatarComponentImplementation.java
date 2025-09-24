package net.eya.penumbra.common.cca;

import net.eya.penumbra.common.util.HealthUtils;
import net.eya.penumbra.foundation.EffectInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class EclipseAvatarComponentImplementation implements EclipseAvatarComponent {
    private boolean isEclipseAvatar = false;
    @Override
    public boolean isAvatar(ServerPlayerEntity player) {
        return isEclipseAvatar;
    }
    @Override
    public void setAvatarValue(boolean value) {
        this.isEclipseAvatar = value;
    }

    @Override
    public void tickAvatarValue(PlayerEntity player) {
        if(isAvatar((ServerPlayerEntity)player)) {
            HealthUtils.addExtraHearts(player, 40.0);
        }
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

    }
    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

    }
}
