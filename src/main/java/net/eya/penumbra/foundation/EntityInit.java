package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.entity.ShackleProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityInit {
    public static void init() {}
    public static final EntityType<ShackleProjectile> SHACKLE_PROJECTILE_ENTITY_TYPE =
            Registry.register(Registries.ENTITY_TYPE, new Identifier(Penumbra.MOD_ID, "shackle_projectile"),
            FabricEntityTypeBuilder.<ShackleProjectile>create(SpawnGroup.MISC, ShackleProjectile::new)
            .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
}
