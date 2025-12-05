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
import team.lodestar.lodestone.systems.particle.world.behaviors.components.DirectionalBehaviorComponent;
import team.lodestar.lodestone.systems.particle.world.behaviors.components.LodestoneBehaviorComponent;

import java.awt.*;

public class AllParticles {
    public static void spawnClawParticles(World level, Vec3d pos) {
        Color startingColor = new Color(255, 228, 67);
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
    public static void obeliskParticles(World level, Vec3d pos) {
        Color startingColor = new Color(255, 240, 154);
        Color endingColor = new Color(216, 167, 82);
        WorldParticleBuilder.create(ParticleInit.GOLDEN_SPARK)
                .setScaleData(GenericParticleData.create(0.2f, 0).setEasing(Easing.EXPO_IN).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, RandomHelper.randomBetween(Random.create(), -1, 1)).build())
                .setLifetime(80)
                .addMotion(RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0.1f, 0.1f), RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0f, 0.2f), RandomHelper.randomBetween(Random.create(), Easing.EXPO_OUT, -0.1f, 0.1f))
                .spawn(level, pos.x, pos.y, pos.z);
    }
    public static void shockwaveParticles(World world, Vec3d pos) {
        Color startColour = new Color(216, 167, 82);
        Color endingColor = new Color(184, 131, 70);
        WorldParticleBuilder.create(ParticleInit.SHOCKWAVE)
                .setScaleData(GenericParticleData.create(1f, 70f).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.85f, 0).build())
                .setColorData(ColorParticleData.create(startColour, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(155)
                .enableNoClip()
                .setBehavior(new DirectionalBehaviorComponent(new Vec3d(0, 90, 0)))
                .spawn(world, pos.x, pos.y, pos.z);
    }
    public static void bloomParticle(World world, Vec3d pos) {
        Color startColour = new Color(248, 209, 109);
        Color endingColor = new Color(216, 167, 82);
        WorldParticleBuilder.create(LodestoneParticleRegistry.THIN_EXTRUDING_SPARK_PARTICLE)
                .setScaleData(GenericParticleData.create(6f).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.85f, 0).build())
                .setColorData(ColorParticleData.create(startColour, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(100)
                .enableNoClip()
                .setBehavior(new DirectionalBehaviorComponent(new Vec3d(0, 0, 0)))
                .spawn(world, pos.x, pos.y + 2, pos.z);
    }
    private static void glowAura(float scale, World world, Vec3d pos, float opacity) {
        Color colour = new Color(216, 167, 82);
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(scale).build())
                .setTransparencyData(GenericParticleData.create(opacity, opacity, 0).build())
                .setColorData(ColorParticleData.create(colour).setCoefficient(1.4f).build())
                .setLifetime(100)
                .enableNoClip()
                .spawn(world, pos.x, pos.y, pos.z);
    }
    public static void executeGlowAura(World world, Vec3d pos) {
        glowAura(1f, world, pos, 0.9f);
        glowAura(3f, world, pos, 0.75f);
        glowAura(5f, world, pos, 0.6f);
        glowAura(7f, world, pos, 0.25f);
    }
}
