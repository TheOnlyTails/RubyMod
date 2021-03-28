package com.theonlytails.rubymod.enchantments

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.EnchantmentRegistry
import net.minecraft.enchantment.*
import net.minecraft.entity.LivingEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

/**
 * Holds the properties of the stinger enchantment.
 *
 * @author TheOnlyTails
 */
class StingerEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentType.WEAPON, arrayOf(EquipmentSlotType.MAINHAND)) {
	override fun getMaxLevel() = 2

	override fun canEnchant(stack: ItemStack) = stack.item == Items.SHEARS || super.canEnchant(stack)

	override fun isTradeable() = false

	override fun checkCompatibility(enchant: Enchantment) =
		super.checkCompatibility(enchant) && enchant != Enchantments.SHARPNESS && enchant != Enchantments.MENDING

	/**
	 * Holds the functionality of the stinger enchantment.
	 *
	 * @author TheOnlyTails
	 */
	@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
	private object StingerEnchantmentEquipped {
		@SubscribeEvent
		fun damageWithEnchant(event: AttackEntityEvent) {
			val mainHandItem = event.player.mainHandItem
			val enchant = EnchantmentRegistry.stinger
			val enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(enchant, mainHandItem)

			if (EnchantmentHelper.getEnchantments(mainHandItem).containsKey(enchant)) {
				val target = event.target
				if (target is LivingEntity) target.addEffect(EffectInstance(Effects.POISON, 100, enchantLevel))
			}
		}
	}
}
