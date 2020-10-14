package com.theonlytails.ruby.enchantments;

import com.theonlytails.ruby.RubyMod;
import com.theonlytails.ruby.registries.EnchantmentRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

public class StingerEnchantment extends Enchantment {
    public StingerEnchantment() {
        super(Rarity.VERY_RARE,
                EnchantmentType.WEAPON,
                new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() == Items.SHEARS || super.canApply(stack);
    }

    @Override
    public boolean canVillagerTrade() {
        return false;
    }

    @Override
    public boolean canApplyTogether(@NotNull Enchantment enchant) {
        return super.canApplyTogether(enchant) &&
               enchant != Enchantments.SHARPNESS &&
               enchant != Enchantments.MENDING;
    }

    @Mod.EventBusSubscriber(modid = RubyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    private static class PoisonedBladeEquipped {
        @SubscribeEvent
        public static void damageWithEnchant(AttackEntityEvent event) {
            PlayerEntity player = event.getPlayer();

            ItemStack heldItemMainhand = player.getHeldItemMainhand();

            Enchantment enchant = EnchantmentRegistry.STINGER.get();

            int enchantLevel = EnchantmentHelper.getEnchantmentLevel(enchant, heldItemMainhand);

            if (EnchantmentHelper.getEnchantments(heldItemMainhand).containsKey(enchant)) {
                if (event.getTarget() instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) event.getTarget();

                    target.addPotionEffect(new EffectInstance(Effects.POISON, 100, enchantLevel));
                }
            }
        }
    }
}
