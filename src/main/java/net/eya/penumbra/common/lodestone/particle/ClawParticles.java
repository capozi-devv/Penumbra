package net.eya.penumbra.common.lodestone.particle;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;


public class ClawParticles {
    public static void spawnClawParticles(World level, Vec3d pos) {
        Color startingColor = new Color(248, 209, 109);
        Color endingColor = new Color(151, 99, 57);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(20)
                .addMotion(RandomHelper.randomBetween(Random.create(), -0.3f, 0.3f), RandomHelper.randomBetween(Random.create(), 0f, 0.5f), RandomHelper.randomBetween(Random.create(), -0.3f, 0.3f))
                .spawn(level, pos.x, pos.y, pos.z);
    }
}
