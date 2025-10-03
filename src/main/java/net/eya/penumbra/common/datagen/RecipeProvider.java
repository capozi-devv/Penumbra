package net.eya.penumbra.common.datagen;

import net.eya.penumbra.foundation.BlockInit;
import net.eya.penumbra.foundation.ItemInit;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, BlockInit.OBELISK, 1)
                .pattern("SNS")
                .pattern("GUG")
                .pattern("NGN")
                .input('N', Items.NETHERITE_INGOT)
                .input('G', Items.GOLD_INGOT)
                .input('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .input('U', ItemInit.UMBRA_CORE)
                .criterion(hasItem(ItemInit.UMBRA_CORE), conditionsFromItem(ItemInit.UMBRA_CORE))
                .offerTo(consumer, new Identifier(getRecipeName(BlockInit.OBELISK)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemInit.ELDRITCH_SHACKLE, 1)
                .pattern("EEE")
                .pattern("EUE")
                .pattern("EEE")
                .input('E', ItemInit.SHACKLE_PIECE)
                .input('U', ItemInit.UMBRA_CORE)
                .criterion(hasItem(ItemInit.UMBRA_CORE), conditionsFromItem(ItemInit.UMBRA_CORE))
                .offerTo(consumer, new Identifier(getRecipeName(ItemInit.ELDRITCH_SHACKLE)));
    }
}
