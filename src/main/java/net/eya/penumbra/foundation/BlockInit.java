package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.block.EclipseObeliskBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockInit {
    public static void init() {}
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Penumbra.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    private static Block registerBlock(String name, Block block, boolean registerBlockItem) {
        if (registerBlockItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, new Identifier(Penumbra.MOD_ID, name), block);
    }
    public static final Block OBELISK = registerBlock("obelisk", new EclipseObeliskBlock(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)), true);
}
