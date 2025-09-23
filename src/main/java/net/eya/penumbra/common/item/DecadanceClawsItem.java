package net.eya.penumbra.common.item;

import net.eya.penumbra.common.particle.ClawParticles;
import net.eya.penumbra.common.util.HealthUtils;
import net.eya.penumbra.common.util.MovementUtils;
import net.eya.penumbra.foundation.EffectInit;
import net.eya.penumbra.foundation.SoundInit;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class DecadanceClawsItem extends SwordItem {
    public DecadanceClawsItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    public List<PlayerEntity> playersWithNecrosis = new ArrayList<>();
    int times = ThreadLocalRandom.current().nextInt(40, 61);
    public static boolean isDashing = false;
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()){
            user.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1f, 1f);
            for(PlayerEntity player : playersWithNecrosis) {
                if (player != null && player.isAlive()) {
                    player.removeStatusEffect(EffectInit.NECROSIS);
                    HealthUtils.reduceMaxHealth(player, 1f);
                    playersWithNecrosis.remove(player);
                    return TypedActionResult.pass(user.getStackInHand(hand));
                }
            }
        } else {
            for (int i = 0; i < times; i++) {
                ClawParticles.spawnClawParticles(user.getWorld(), user.getPos());
            }
            MovementUtils.dashPlayer(user, 2);
            user.playSound(SoundInit.CLAW_SLASH, SoundCategory.PLAYERS, 1f, 1f);
            user.getItemCooldownManager().set(this, 100);
            isDashing = true;// man fuck chatgpt
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target instanceof PlayerEntity) {
            target.addStatusEffect(new StatusEffectInstance(EffectInit.NECROSIS, 600, 1, false, false, false));
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
        return super.onLeftClickEntity(stack, player, entity);
    }
}
