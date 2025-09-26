package net.eya.penumbra.common.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class LackeyComponentImplementation implements LackeyComponent {
    private boolean isLackey = false;
    @Override
    public boolean isLackey(ServerPlayerEntity player) {
        return isLackey;
    }
    @Override
    public void setLackey(boolean value) {
        this.isLackey = value;
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

    }
}
