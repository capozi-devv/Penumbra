package net.eya.penumbra.common.item;

import net.eya.penumbra.common.lodestone.particle.AllParticles;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

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
