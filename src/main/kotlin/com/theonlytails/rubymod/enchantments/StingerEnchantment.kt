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

	override fun canApply(stack: ItemStack) = stack.item == Items.SHEARS || super.canApply(stack)

	override fun canVillagerTrade() = false

	public override fun canApplyTogether(enchant: Enchantment) =
		super.canApplyTogether(enchant) && enchant != Enchantments.SHARPNESS && enchant != Enchantments.MENDING

	/**
	 * Holds the functionality of the stinger enchantment.
	 *
	 * @author TheOnlyTails
	 */
	@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
	private object PoisonedBladeEquipped {
		@SubscribeEvent
		fun damageWithEnchant(event: AttackEntityEvent) {
			val heldItemMainhand = event.player.heldItemMainhand
			val enchant = EnchantmentRegistry.stinger
			val enchantLevel = EnchantmentHelper.getEnchantmentLevel(enchant, heldItemMainhand)

			if (EnchantmentHelper.getEnchantments(heldItemMainhand).containsKey(enchant)) {
				val target = event.target
				if (target is LivingEntity) {
					target.addPotionEffect(EffectInstance(Effects.POISON, 100, enchantLevel))
				}
			}
		}
	}
}
