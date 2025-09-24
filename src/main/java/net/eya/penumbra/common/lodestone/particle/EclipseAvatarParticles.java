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

public class EclipseAvatarParticles {
    public static void afterEffectParticles(World world, Vec3d pos) {
        Color startingColor = new Color(248, 209, 109);
        Color endingColor = new Color(118, 101, 89);
        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.25f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.75f, 0.25f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setLifetime(50)
                .spawn(world, pos.x, pos.y, pos.z)
                .spawn(world, pos.x + 0.25f, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z)
                .spawn(world, pos.x, pos.y, pos.z);
    }
}
