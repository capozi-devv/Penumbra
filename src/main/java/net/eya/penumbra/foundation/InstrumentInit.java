package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.minecraft.item.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import static net.eya.penumbra.foundation.SoundInit.WARHORN;

public class InstrumentInit {
    public static final RegistryKey<SoundEvent> WARHORN_KEY = RegistryKey.of(RegistryKeys.SOUND_EVENT, new Identifier(Penumbra.MOD_ID, "warhorn"));
    public static final Instrument WARHORN = new Instrument(RegistryEntry.of(SoundInit.WARHORN), 10, 10f);
    public static void init() {
        Registry.register(Registries.INSTRUMENT, WARHORN_KEY.getValue(), WARHORN);
    }
}
