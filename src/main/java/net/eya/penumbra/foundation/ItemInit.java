package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.item.DecadanceClawsItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemInit {
    public static void init() {}
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Penumbra.MOD_ID, name), item);
    }
    public static final Item DECADENCE_CLAWS = registerItem("claws_of_decadence", new DecadanceClawsItem(ToolMaterials.NETHERITE, 4, -2.6f, new Item.Settings().maxCount(1)));
}
