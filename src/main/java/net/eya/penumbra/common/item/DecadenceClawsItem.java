package net.eya.penumbra.common.item;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.common.util.HealthUtils;
import net.eya.penumbra.common.util.MovementUtils;
import net.eya.penumbra.foundation.DamageTypeInit;
import net.eya.penumbra.foundation.EffectInit;
import net.eya.penumbra.foundation.SoundInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class DecadenceClawsItem extends SwordItem {
    public DecadenceClawsItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    public List<PlayerEntity> playersWithNecrosis = new ArrayList<>();
    int times = ThreadLocalRandom.current().nextInt(40, 61);
    public static Boolean isDashing = false;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()){
            user.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1f, 1f);
            int duration = 5 * playersWithNecrosis.toArray().length;
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, duration, 1, false, false, true));
            for(PlayerEntity player : playersWithNecrosis) {
                if (player != null && player.isAlive()) {
                    player.removeStatusEffect(EffectInit.NECROSIS);
                    HealthUtils.reduceMaxHealth(player, 1f, true);
                    playersWithNecrosis.remove(player);
                    return TypedActionResult.pass(user.getStackInHand(hand));
                }
            }
            user.getItemCooldownManager().set(this, 160);
        } else {
            for (int i = 0; i < times; i++) {
                AllParticles.spawnClawParticles(user.getWorld(), user.getPos());
                AllParticles.spawnClawParticlesB(user.getWorld(), user.getPos());
            }
            user.setVelocity(Vec3d.ZERO);
            MovementUtils.dashPlayer(user, 2);
            if(!user.getAbilities().creativeMode) {
                user.getItemCooldownManager().set(this, 50);
            }
            isDashing = true;
            user.playSound(SoundInit.CLAW_SLASH, SoundCategory.PLAYERS, 1f, 1f);
            user.swingHand(hand, true);
            HitResult hit = user.raycast(5.0D, 0.0F, false);
            if (hit.getType() == HitResult.Type.ENTITY) {
                EntityHitResult ehr = (EntityHitResult) hit;
                user.attack(ehr.getEntity());
            } // man fuck chatgpt
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        DamageSource source = new DamageSource(attacker.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypeInit.CLAW_DAMAGE));
        target.damage(source, this.getAttackDamage());
        if(target instanceof PlayerEntity) {
            target.addStatusEffect(new StatusEffectInstance(EffectInit.NECROSIS, 600, 1, false, true, true));
            if (!playersWithNecrosis.contains(target)) {
                PlayerEntity castedPlayer = (PlayerEntity) target;
                playersWithNecrosis.add(castedPlayer);
            }
        }
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        player.playSound(SoundInit.CLAW_ATTACK, SoundCategory.PLAYERS, 1f, 1f);
        int amount = ThreadLocalRandom.current().nextInt(4, 6);
        for(int i = 0; i < amount; i++) {
            AllParticles.spawnClawParticles(player.getWorld(), entity.getPos());
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && isDashing) {
            if(((entity) instanceof PlayerEntity playerEntity)) {
                for (int i = 0; i < 21; i++) {
                    if((playerEntity).getServer() != null) {
                        if((playerEntity).getServer().getTicks() % (2 + i) == 0) {
                            if(isDashing) {
                                AllParticles.spawnClawParticles(world, playerEntity.getPos());
                                AllParticles.spawnClawParticlesB(world, playerEntity.getPos());
                            }
                            if(i >= 20) {
                                isDashing = false;
                            }
                        }
                    } else if(MinecraftClient.getInstance().world.getTime() % (2 + i) == 0) {
                        if(isDashing) {
                            AllParticles.spawnClawParticles(world, playerEntity.getPos());
                            AllParticles.spawnClawParticlesB(world, playerEntity.getPos());
                        }
                        if(i >= 20) {
                            isDashing = false;
                        }
                    }
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
