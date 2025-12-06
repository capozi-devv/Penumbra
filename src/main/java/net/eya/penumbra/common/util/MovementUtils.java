package net.eya.penumbra.common.util;

import net.eya.penumbra.common.lodestone.particle.AllParticles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.concurrent.ThreadLocalRandom;

public class MovementUtils {
    static int times = ThreadLocalRandom.current().nextInt(10, 21);
    public static void dashPlayer(PlayerEntity player, double strength) {
        if (player == null) return;
        Vec3d look = player.getRotationVec(1.0F).normalize();
        Vec3d dashVelocity = new Vec3d(look.x * strength, look.y * strength, look.z * strength);
        player.addVelocity(dashVelocity.x, dashVelocity.y, dashVelocity.z);
        player.velocityModified = true;
    }
//    public static void storeMomentum(PlayerEntity player) {
//        if(player == null) return;
//        player.setVelocity(Vec3d.ZERO);
//        for (int i = 0; i < times; i++) {
//            AllParticles.spawnClawParticles(player.getWorld(), player.getPos());
//        }
//        player.velocityModified = true;
//    }
}
