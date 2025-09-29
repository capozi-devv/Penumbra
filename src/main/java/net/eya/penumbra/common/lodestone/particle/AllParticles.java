package net.eya.penumbra.common.lodestone.particle;

import net.eya.penumbra.foundation.ParticleInit;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import team.lodestar.lodestone.helpers.EasingHelper;
import team.lodestar.lodestone.helpers.RandomHelper;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.world.behaviors.BillboardParticleBehavior;
import team.lodestar.lodestone.systems.particle.world.behaviors.components.DirectionalBehaviorComponent;
import team.lodestar.lodestone.systems.particle.world.behaviors.components.ExtrudingSparkBehaviorComponent;
import team.lodestar.lodestone.systems.particle.world.behaviors.components.LodestoneBehaviorComponent;

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
    public static void obeliskAuraParticles(World world, Vec3d pos) {
        Color congruent = new Color(126, 116, 112);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.85f, 0).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.15f).build())
                .setColorData(ColorParticleData.create(congruent, congruent).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(40)
                .addMotion(RandomHelper.randomBetween(Random.create(), -0.3f, 0.3f), RandomHelper.randomBetween(Random.create(), Easing.CIRC_IN, 0.5f, 1f), RandomHelper.randomBetween(Random.create(), -0.3f, 0.3f))
                .spawn(world, pos.x, pos.y, pos.z);
    }
    public static void shockwaveParticles(World world, Vec3d pos) {
        Color startColour = new Color(216, 167, 82);
        Color endingColor = new Color(184, 131, 70);
        WorldParticleBuilder.create(ParticleInit.SHOCKWAVE)
                .setScaleData(GenericParticleData.create(1f, 10, 15f).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.85f, 0).build())
                .setColorData(ColorParticleData.create(startColour, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(40)
                .enableNoClip()
                .setBehavior(new DirectionalBehaviorComponent(new Vec3d(0, 90, 0)))
                .spawn(world, pos.x, pos.y, pos.z);
    }
    public static void bloomParticle(World world, Vec3d pos) {
        Color startColour = new Color(248, 209, 109);
        Color endingColor = new Color(216, 167, 82);
        WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setScaleData(GenericParticleData.create(6f).build())
                .setTransparencyData(GenericParticleData.create(0.85f, 0.85f, 0).build())
                .setColorData(ColorParticleData.create(startColour, endingColor).setCoefficient(1.4f).build())
                .setSpinData(SpinParticleData.create(0f, 0f).build())
                .setLifetime(100)
                .enableNoClip()
                .setBehavior(LodestoneBehaviorComponent.EXTRUDING_SPARK)
                .spawn(world, pos.x, pos.y, pos.z);
    }
}
