package net.eya.penumbra.foundation;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.eya.penumbra.Penumbra;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import team.lodestar.lodestone.systems.particle.world.type.LodestoneWorldParticleType;

public class ParticleInit {
    public static void init() {}
    public static LazyRegistrar<ParticleType<?>> PARTICLES = LazyRegistrar.create(Registries.PARTICLE_TYPE, Penumbra.MOD_ID);
    public static RegistryObject<LodestoneWorldParticleType> GOLDEN_SPARK = PARTICLES.register("golden_spark", LodestoneWorldParticleType::new);
    public static RegistryObject<LodestoneWorldParticleType> GOLDEN_STAR = PARTICLES.register("golden_star", LodestoneWorldParticleType::new);

    public static void registerParticleFactory() {
        ParticleFactoryRegistry.getInstance().register(GOLDEN_SPARK.get(), LodestoneWorldParticleType.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GOLDEN_STAR.get(), LodestoneWorldParticleType.Factory::new);
    }
}
