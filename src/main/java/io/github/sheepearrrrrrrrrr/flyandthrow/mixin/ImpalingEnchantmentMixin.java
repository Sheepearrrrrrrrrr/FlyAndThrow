package io.github.sheepearrrrrrrrrr.flyandthrow.mixin;

import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EntityGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ImpalingEnchantment.class)
public class ImpalingEnchantmentMixin {
    public ImpalingEnchantmentMixin() {
    }

    @Inject(
            method = {"getAttackDamage"},
            at = {@At("HEAD")},
            cancellable = true
    )
    void flyandthrow$getAttackDamage(int level, EntityGroup group, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0.0F);
    }
}
