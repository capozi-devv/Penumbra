package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.item.*;
import net.eya.penumbra.common.item.material.EclipseArmourMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class ItemInit {
    public static void init() {}
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Penumbra.MOD_ID, name), item);
    }
    public static final Item DECADENCE_CLAWS = registerItem("claws_of_decadence", new DecadenceClawsItem(ToolMaterials.NETHERITE, 4, -2.6f, new Item.Settings().maxCount(1)));
    public static final Item RITUAL_DAGGER = registerItem("ritual_dagger", new RitualDaggerItem(new Item.Settings().maxCount(1)));
    public static final Item ECLIPSE_HELMET = registerItem("eclipse_helmet", new ArmorItem(EclipseArmourMaterial.ECLIPSE_ARMOUR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item ECLIPSE_CHESTPLATE = registerItem("eclipse_chestplate", new ArmorItem(EclipseArmourMaterial.ECLIPSE_ARMOUR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item ECLIPSE_LEGGINGS = registerItem("eclipse_leggings", new ArmorItem(EclipseArmourMaterial.ECLIPSE_ARMOUR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item ECLIPSE_BOOTS = registerItem("eclipse_boots", new ArmorItem(EclipseArmourMaterial.ECLIPSE_ARMOUR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()));
    public static final Item TEST_ITEM = registerItem("test", new TestItem(new Item.Settings()));
    public static final Item SERVITUDE_TOKEN = registerItem("token_of_servitude", new ServitudeTokenItem(new Item.Settings().maxCount(1)));
    public static final Item UMBRA_CORE = registerItem("umbra_shard", new Item(new Item.Settings().maxCount(16).fireproof()));
    public static final Item SHACKLE_PIECE = registerItem("shackle_piece", new Item(new Item.Settings().fireproof()));
    public static final Item ELDRITCH_SHACKLE = registerItem("eldritch_shackle", new EldritchShackleItem(new Item.Settings().maxCount(1).fireproof()));
}
