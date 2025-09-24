package net.eya.penumbra.foundation;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DamageTypeInit {
    public static void init() {}
    public static final RegistryKey<DamageType> DAGGER_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("penumbra", "dagger"));
}
