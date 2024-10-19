package io.github.sheepearrrrrrrrrr.flyandthrow.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    public MobEntityMixin() {
    }

    @Redirect(
            method = {"tryAttack(Lnet/minecraft/entity/Entity;)Z"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/MobEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D",
                    ordinal = 0
            )
    )
    public double tryAttack(MobEntity instance, EntityAttribute entityAttribute, Entity entity) {
        float f = (float)instance.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (entity.isTouchingWaterOrRain()) {
            f += (float) EnchantmentHelper.getLevel(Enchantments.IMPALING, instance.getMainHandStack()) * 3.5F;
        }

        return (double)f;
    }
}
