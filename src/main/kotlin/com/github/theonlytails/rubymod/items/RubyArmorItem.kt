package com.github.theonlytails.rubymod.items

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.ItemRegistry
import com.github.theonlytails.rubymod.util.enums.RubyArmorMaterial
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.world.World
import java.util.stream.StreamSupport

/**
 * Holds the custom functionality of ruby armor.
 *
 * @author TheOnlyTails
 */
class RubyArmorItem(slot: EquipmentSlotType) :
	ArmorItem(RubyArmorMaterial.RUBY, slot, RubyMod.RUBY_TAB_PROPERTY) {

	override fun onArmorTick(stack: ItemStack, world: World, player: PlayerEntity) {
		val heldItemMainhand = player.heldItemMainhand.item
		val armor = player.armorInventoryList

		val wearingAllRubyArmor = StreamSupport.stream(armor.spliterator(), false)
			.allMatch { itemStack: ItemStack -> itemStack.item is RubyArmorItem }

		if (wearingAllRubyArmor && heldItemMainhand === ItemRegistry.RUBY_PICKAXE) {
			player.addPotionEffect(EffectInstance(Effects.HASTE, 220, 0, true, true))
		}
	}
}