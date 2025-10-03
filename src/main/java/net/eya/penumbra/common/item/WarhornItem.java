package net.eya.penumbra.common.item;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.foundation.DamageTypeInit;
import net.eya.penumbra.foundation.InstrumentInit;
import net.eya.penumbra.foundation.ItemInit;
import net.eya.penumbra.foundation.SoundInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World
        ;
import team.lodestar.lodestone.handlers.ScreenshakeHandler;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.screenshake.PositionedScreenshakeInstance;

import java.util.List;

public class WarhornItem extends GoatHornItem {
    public WarhornItem(Settings settings, TagKey<Instrument> instrumentTag) {
        super(settings, instrumentTag);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world != null) {
            DamageSource source = new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypeInit.WARHORN_DAMAGE));
            AllParticles.shockwaveParticles(world, user.getPos().offset(Direction.UP, 1));
            ScreenshakeHandler.addScreenshake(new PositionedScreenshakeInstance(30, user.getPos(), 5, 10).setIntensity(7).setEasing(Easing.LINEAR));
            playSound(world, user, InstrumentInit.WARHORN);
            user.getItemCooldownManager().set(ItemInit.WARHORN, 1000);
            Vec3d center = user.getPos();
            double radius = 8.0;
            Box box = new Box(
                    center.x - radius, center.y - (radius/2), center.z - radius,
                    center.x + radius, center.y + (radius/2), center.z + radius
            );
            List<LivingEntity> entities = user.getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    box,
                    e -> e.squaredDistanceTo(center) <= radius * radius
            );
            for (LivingEntity entity : entities) {
                double x = user.getX() - entity.getX();
                double z = user.getZ() - entity.getZ();
                if (entity != user) {
                    entity.damage(source, 6);
                    entity.takeKnockback(7, x, z);
                }
            }
            return super.use(world, user, hand);
        }
        return super.use(world, user, hand);
    }
    private static void playSound(World world, PlayerEntity player, Instrument instrument) {
        SoundEvent sound = SoundInit.WARHORN;
        float f = instrument.range() / 16.0F;
        world.playSoundFromEntity(player, player, sound, SoundCategory.RECORDS, f, 0.3F);
    }
}
