package net.eya.penumbra.common.item;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.PenumbraComponents;
import net.eya.penumbra.common.cca.LackeyComponent;
import net.eya.penumbra.common.util.HealthUtils;
import net.eya.penumbra.foundation.DamageTypeInit;
import net.eya.penumbra.foundation.ItemInit;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RitualDaggerItem extends Item {
    public RitualDaggerItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        DamageSource source = new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypeInit.DAGGER_DAMAGE));
        user.damage(source, 2f);
        LackeyComponent lackeyComponent = Penumbra.getLackey().get(user);
        HealthUtils.removeExtraHearts(user);
        HealthUtils.reduceMaxHealth(user, 2f, false);
        lackeyComponent.setLackey(true);
        ItemStack tokenStack = new ItemStack(ItemInit.SERVITUDE_TOKEN);
        tokenStack.getOrCreateNbt().putUuid("Servant", user.getUuid());
        tokenStack.getOrCreateNbt().putString("ServantName", user.getName().getString());
        ItemEntity itemEntity = new ItemEntity(world, user.getX(), user.getY() + 1.0, user.getZ(), tokenStack);
        world.spawnEntity(itemEntity);
        return super.use(world, user, hand);
    }
}
