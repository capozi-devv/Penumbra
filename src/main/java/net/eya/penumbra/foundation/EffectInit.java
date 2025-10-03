package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.effect.NecrosisEffect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EffectInit {
    public static void init() {}
    public static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Penumbra.MOD_ID, name),statusEffect);
    }
    public static final StatusEffect NECROSIS = new NecrosisEffect(StatusEffectCategory.HARMFUL, -16777216);
    private static final RegistryEntry<StatusEffect> NECROSIS_EFFECT = registerStatusEffect("decadence", NECROSIS);
}
