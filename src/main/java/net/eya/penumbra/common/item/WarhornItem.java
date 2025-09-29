package net.eya.penumbra.common.item;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.foundation.ItemInit;
import net.eya.penumbra.foundation.SoundInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.systems.screenshake.PositionedScreenshakeInstance;
import team.lodestar.lodestone.systems.screenshake.ScreenshakeInstance;

public class WarhornItem extends GoatHornItem {
    public WarhornItem(Settings settings, TagKey<Instrument> instrumentTag) {
        super(settings, instrumentTag);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world != null) {
            // world.playSound(null, user.getBlockPos(), SoundInit.WARHORN, SoundCategory.PLAYERS, 0.2f, 0.5f);
            AllParticles.shockwaveParticles(world, user.getPos());
            ScreenshakeHandler.addScreenshake(new PositionedScreenshakeInstance(30, user.getPos(), 5, 10).setIntensity(10));
            user.getItemCooldownManager().set(ItemInit.WARHORN, 80);
            return super.use(world, user, hand);
        }
        return super.use(world, user, hand);
    }
    private static void playSound(World world, PlayerEntity player, Instrument instrument) {
        SoundEvent sound = SoundInit.WARHORN;
        float f = instrument.range() / 16.0F;
        world.playSoundFromEntity(player, player, sound, SoundCategory.RECORDS, f, 1.0F);
    }
}
