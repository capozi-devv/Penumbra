package net.eya.penumbra.foundation;

import net.eya.penumbra.Penumbra;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroups {
    public static void init() {}
    public static final RegistryKey<ItemGroup> PENUMBRA_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(), new Identifier(Penumbra.MOD_ID, "item_group"));
    public static final ItemGroup MAISONNETTE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Penumbra.MOD_ID, "penumbra"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.penumbra"))
                    .icon(() -> new ItemStack(ItemInit.RITUAL_DAGGER)).entries((displayContext, entries) -> {
                        entries.add(BlockInit.OBELISK);
                        entries.add(ItemInit.ECLIPSE_HELMET);
                        entries.add(ItemInit.ECLIPSE_CHESTPLATE);
                        entries.add(ItemInit.ECLIPSE_LEGGINGS);
                        entries.add(ItemInit.ECLIPSE_BOOTS);
                        entries.add(ItemInit.DECADENCE_CLAWS);
                        entries.add(ItemInit.RITUAL_DAGGER);
            })
            .build());

}
