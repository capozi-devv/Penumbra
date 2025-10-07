package net.eya.penumbra.common.cca;

import net.eya.penumbra.common.util.HealthUtils;

import net.minecraft.entity.attribute.EntityAttributes;
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
            var attribute = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            if(attribute != null) {
                if (attribute.getModifier(HealthUtils.EXTRA_HEARTS_UUID) == null) {
                    HealthUtils.addExtraHearts(player, 40.0);
                }
            }
        }
    }
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

    }
    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

    }
}
