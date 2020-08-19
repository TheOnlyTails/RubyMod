package com.theonlytails.ruby.items;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.init.ToolsRegistry;
import com.theonlytails.ruby.util.enums.RubyArmorMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class RubyArmorItem extends ArmorItem {
    public RubyArmorItem(EquipmentSlotType slot) {
        super(RubyArmorMaterial.RUBY, slot, new Properties().group(TheOnlyTails.RUBY));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        Item itemHeldMainhand = player.getHeldItemMainhand().getItem();
        Iterable<ItemStack> armor = player.getArmorInventoryList();
        AtomicInteger numOfRubyArmor = new AtomicInteger(0);

        armor.forEach(itemStack -> {
            if (itemStack.getItem() instanceof RubyArmorItem) {
                numOfRubyArmor.getAndIncrement();
            }
        });

        if (numOfRubyArmor.get() == 4 && itemHeldMainhand == ToolsRegistry.RUBY_PICKAXE.get()) {
            player.addPotionEffect(new EffectInstance(Effects.HASTE, 220, 0, true, true));
        }
    }
}
