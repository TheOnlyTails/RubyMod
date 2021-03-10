package com.theonlytails.rubymod.items

import com.theonlytails.rubymod.registries.ItemRegistry
import com.theonlytails.rubymod.rubyTabProperty
import com.theonlytails.rubymod.util.enums.RubyArmorMaterial
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.world.World

/**
 * Holds the custom functionality of ruby armor.
 *
 * @author TheOnlyTails
 */
class RubyArmor(slot: EquipmentSlotType) :
	ArmorItem(RubyArmorMaterial.RUBY, slot, rubyTabProperty) {

	override fun onArmorTick(stack: ItemStack, world: World, player: PlayerEntity) {
		val mainHandItem = player.mainHandItem.item
		val armor = player.armorSlots

		val wearingAllRubyArmor = armor.all { it.item is RubyArmor }

		if (wearingAllRubyArmor && mainHandItem == ItemRegistry.rubyPickaxe)
			player.addEffect(EffectInstance(Effects.DIG_SPEED, 220, 0, true, true))
	}
}
