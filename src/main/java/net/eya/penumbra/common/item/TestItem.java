package net.eya.penumbra.common.item;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user != null) {
            EclipseAvatarComponent avatarComponent = Penumbra.getEclipseAvatar().get(user);
            avatarComponent.setAvatarValue(true);
            return TypedActionResult.success(user.getStackInHand(hand), false);
        }
        return super.use(world, user, hand);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
