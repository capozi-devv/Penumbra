package net.eya.penumbra.common.lodestone.particle;

import net.eya.penumbra.foundation.ParticleInit;
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

public class AllParticles {
    public static void spawnClawParticles(World level, Vec3d pos) {
        Color startingColor = new Color(255, 240, 154);
        Color endingColor = new Color(216, 167, 82);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f, 0).setEasing(Easing.QUARTIC_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(80)
                .addMotion(RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f), RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f), RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f))
                .spawn(level, pos.x+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f), pos.y+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f), pos.z+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f));
    }
    public static void spawnClawParticlesB(World level, Vec3d pos) {
        Color startingColor = new Color(255, 240, 154);
        Color endingColor = new Color(216, 167, 82);
        WorldParticleBuilder.create(ParticleInit.GOLDEN_SPARK)
                .setScaleData(GenericParticleData.create(0.5f, 0).setEasing(Easing.QUARTIC_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(80)
                .addMotion(RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f), RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f), RandomHelper.randomBetween(Random.create(), -0.05f, 0.05f))
                .spawn(level, pos.x+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f), pos.y+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f), pos.z+RandomHelper.randomBetween(Random.create(), -1.5f, 1.5f));
    }
    public static void afterEffectParticles(World world, Vec3d pos) {
        Color startingColor = new Color(248, 209, 109);
        Color endingColor = new Color(118, 101, 89);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.25f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setLifetime(50)
                .spawn(world, pos.x, pos.y, pos.z) // spawn at different locations on the player
                .spawn(world, pos.x + 0.25f, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z);
    }
    public static void obeliskParticles(World level, Vec3d pos) {
        Color startingColor = new Color(216, 167, 82);
        Color endingColor = new Color(184, 131, 70);
        WorldParticleBuilder.create(ParticleInit.GOLDEN_SPARK)
                .setScaleData(GenericParticleData.create(0.2f, 0).setEasing(Easing.EXPO_IN).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, RandomHelper.randomBetween(Random.create(), -1, 1)).build())
                .setLifetime(80)
                .addMotion(RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0.1f, 0.1f), RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0f, 0.2f), RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0.1f, 0.1f))
                .spawn(level, pos.x, pos.y, pos.z);
    }
    public static void obeliskAuraParticles(World world, Vec3d pos) {
        Color congruent = new Color(126, 116, 112);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.85f, 0).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.15f).build())
                .setColorData(ColorParticleData.create(congruent, congruent).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(40)
                .addMotion(RandomHelper.randomBetween(Random.create(), -0.015f, 0.015f), RandomHelper.randomBetween(Random.create(), Easing.CIRC_IN, 0.5f, 1f), RandomHelper.randomBetween(Random.create(), -0.015f, 0.015f))
                .spawn(world, pos.x, pos.y, pos.z);
    }
}
