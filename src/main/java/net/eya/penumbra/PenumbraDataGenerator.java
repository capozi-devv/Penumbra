package net.eya.penumbra;

import net.eya.penumbra.common.datagen.LootTableProvider;
import net.eya.penumbra.common.datagen.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PenumbraDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(LootTableProvider::new);
    }
}
