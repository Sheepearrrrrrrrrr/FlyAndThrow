package io.github.sheepearrrrrrrrrr.flyandthrow.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{
    public PlayerEntityMixin(World world) {
        super(EntityType.PLAYER, world);
    }

    @Redirect(
            method = {"attack(Lnet/minecraft/entity/Entity;)V"},
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"
            )
    )
    public float attack(ItemStack stack, EntityGroup group, Entity target) {
        float h;
        if (target instanceof LivingEntity) {
            h = EnchantmentHelper.getAttackDamage(stack, ((LivingEntity)target).getGroup());
        } else {
            h = EnchantmentHelper.getAttackDamage(stack, EntityGroup.DEFAULT);
        }

        if (target.isTouchingWaterOrRain()) {
            h += (float)EnchantmentHelper.getLevel(Enchantments.IMPALING, stack) * 3.5F;
        }

        return h;
    }

    private void updateTurtleHelmet() {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
        if (itemStack.isOf(Items.TURTLE_HELMET)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200, 0, false, false, true));
        }

    }
}
