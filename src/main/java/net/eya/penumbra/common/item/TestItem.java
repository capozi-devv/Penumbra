package net.eya.penumbra.common.item;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        AllParticles.bloomParticle(context.getWorld(), context.getHitPos());
        return ActionResult.success(true);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
