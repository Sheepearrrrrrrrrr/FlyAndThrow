package io.github.sheepearrrrrrrrrr.flyandthrow.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TridentEntity.class)
public class TridentEntityMixin {
    public TridentEntityMixin(){
    }

    @Redirect(
            method = {"onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"
            )
    )
    public float onEntityHit(ItemStack stack, EntityGroup group, EntityHitResult entityHitResult) {
        float f = EnchantmentHelper.getAttackDamage(stack, group);
        if (entityHitResult.getEntity() instanceof LivingEntity && entityHitResult.getEntity().isTouchingWaterOrRain()) {
            f = (float)EnchantmentHelper.getLevel(Enchantments.IMPALING, stack) * 3.5F;
        }

        return f;
    }
}
