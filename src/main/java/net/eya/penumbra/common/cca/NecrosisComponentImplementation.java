package net.eya.penumbra.common.cca;

import net.eya.penumbra.common.effect.NecrosisEffect;
import net.eya.penumbra.foundation.EffectInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class NecrosisComponentImplementation implements NecrosisComponent{
    private boolean hasNecrosis = false;
    @Override
    public boolean hasNecrosis(ServerPlayerEntity player) {
        if(player.hasStatusEffect(EffectInit.NECROSIS)) {
            return true;
        }
        return false;
    }
    @Override
    public void setNecrosis(boolean value) {
        this.hasNecrosis = value;
    }
    @Override
    public void readFromNbt(NbtCompound nbtCompound) {

    }
    @Override
    public void writeToNbt(NbtCompound nbtCompound) {

    }
}
