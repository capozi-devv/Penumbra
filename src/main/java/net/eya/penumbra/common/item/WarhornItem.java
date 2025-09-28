package net.eya.penumbra.common.item;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.foundation.SoundInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WarhornItem extends Item {
    public WarhornItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world != null) {
            world.playSound(null, user.getBlockPos(), SoundInit.WARHORN, SoundCategory.PLAYERS, 1f, 1f);
            AllParticles.shockwaveParticles(world, user.getPos());
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
}
