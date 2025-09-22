package net.eya.penumbra.common.item;

import net.eya.penumbra.common.util.HealthUtils;
import net.eya.penumbra.foundation.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DecadanceClawsItem extends SwordItem {
    public DecadanceClawsItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    public List<PlayerEntity> playersWithNecrosis = new ArrayList<>();
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        for(PlayerEntity player : playersWithNecrosis) {
            if (player != null && player.isAlive()) {
                player.removeStatusEffect(EffectInit.NECROSIS);
                HealthUtils.reduceMaxHealth(player, 1f);
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
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
}
