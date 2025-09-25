package net.eya.penumbra;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.eya.penumbra.foundation.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Penumbra implements ModInitializer {
    public static final String MOD_ID = "penumbra";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
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
        LOGGER.info("Capozi I'm begging you to tell us what you're doing");
    }
}
