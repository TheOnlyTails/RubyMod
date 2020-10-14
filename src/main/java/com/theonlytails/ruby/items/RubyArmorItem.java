package com.theonlytails.ruby.items;

import com.theonlytails.ruby.RubyMod;
import com.theonlytails.ruby.registries.ItemRegistry;
import com.theonlytails.ruby.util.enums.RubyArmorMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.stream.StreamSupport;

public class RubyArmorItem extends ArmorItem {
    public RubyArmorItem(EquipmentSlotType slot) {
        super(RubyArmorMaterial.RUBY, slot, RubyMod.RUBY_TAB_PROPERTY);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        Item heldItemMainhand = player.getHeldItemMainhand().getItem();
        Iterable<ItemStack> armor = player.getArmorInventoryList();

        boolean wearingAllRubyArmor = StreamSupport.stream(armor.spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof RubyArmorItem);

        if (wearingAllRubyArmor && heldItemMainhand == ItemRegistry.RUBY_PICKAXE.get()) {
            player.addPotionEffect(new EffectInstance(Effects.HASTE, 220, 0, true, true));
        }
    }
}
