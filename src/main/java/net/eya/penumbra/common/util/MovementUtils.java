package net.eya.penumbra.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class MovementUtils {
    public static void dashPlayer(PlayerEntity player, double strength) {
        if (player == null) return;
        Vec3d look = player.getRotationVec(1.0F).normalize();
        Vec3d dashVelocity = new Vec3d(look.x * strength, look.y * strength, look.z * strength);
        player.addVelocity(dashVelocity.x, dashVelocity.y, dashVelocity.z);
        player.velocityModified = true;
    }
}
