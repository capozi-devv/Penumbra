package net.eya.penumbra;

import net.eya.penumbra.foundation.BlockInit;
import net.eya.penumbra.foundation.EffectInit;
import net.eya.penumbra.foundation.ItemInit;
import net.fabricmc.api.ModInitializer;

public class Penumbra implements ModInitializer {
    public static final String MOD_ID = "penumbra";
    @Override
    public void onInitialize() {
        BlockInit.init();
        EffectInit.init();
        ItemInit.init();
    }
}
