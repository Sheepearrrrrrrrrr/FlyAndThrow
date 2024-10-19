package io.github.sheepearrrrrrrrrr.flyandthrow.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.RiptideEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RiptideEnchantment.class)
public class RiptideEnchantmentMixin extends Enchantment {
    public RiptideEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
