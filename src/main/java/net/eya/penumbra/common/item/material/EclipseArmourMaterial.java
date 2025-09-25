package net.eya.penumbra.common.item.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class EclipseArmourMaterial implements ArmorMaterial {
    public static final EclipseArmourMaterial INSTANCE = new EclipseArmourMaterial();
    @Override
    public int getDurability(ArmorItem.Type type) {
        return Integer.MAX_VALUE;
    }
    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 4;
            case CHESTPLATE -> 9;
            case LEGGINGS -> 7;
            case BOOTS -> 3;
        };
    }
    @Override
    public int getEnchantability() {
        return 0;
    }
    @Override
    public SoundEvent getEquipSound() {
        return null;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
    @Override
    public String getName() {
        return "eclipse_material";
    }
    @Override
    public float getToughness() {
        return 3;
    }
    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
