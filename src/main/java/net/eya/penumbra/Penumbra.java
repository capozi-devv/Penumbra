package net.eya.penumbra;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.eya.penumbra.foundation.*;
import net.fabricmc.api.ModInitializer;

public class Penumbra implements ModInitializer {
    public static final String MOD_ID = "penumbra";
    public static ComponentKey<EclipseAvatarComponent> ECLIPSE_AVATAR;
    public static ComponentKey<EclipseAvatarComponent> getEclipseAvatar() { return ECLIPSE_AVATAR; }
    @Override
    public void onInitialize() {
        BlockInit.init();
        EffectInit.init();
        ItemInit.init();
        SoundInit.init();
        DamageTypeInit.init();
        ItemGroups.init();
    }
}
