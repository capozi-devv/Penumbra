package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundInit {
    public static void init() {}
    private static SoundEvent registerSoundEvents(String name) {
        Identifier id = new Identifier(Penumbra.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static final SoundEvent CLAW_SLASH = registerSoundEvents("claw_slash");
    public static final SoundEvent CLAW_ATTACK = registerSoundEvents("claw_attack");
    public static final SoundEvent WARHORN = registerSoundEvents("warhorn");
}
