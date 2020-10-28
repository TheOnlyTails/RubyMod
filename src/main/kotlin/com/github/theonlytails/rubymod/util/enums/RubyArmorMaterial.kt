package com.github.theonlytails.rubymod.util.enums

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.IArmorMaterial
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.SoundEvent
import net.minecraft.util.SoundEvents
import net.minecraftforge.common.util.Lazy
import javax.annotation.Nonnull

/**
 * Holds the properties of any ruby armor piece.
 *
 * @author TheOnlyTails
 *
 * @property materialName The name of this armor material.
 * @property maxDamageFactor Determines the durability of each piece.
 * @property damageReductionAmount Determines how much each piece can block damage.
 * @property enchantability Determines how good the enchantments will be when enchanting this armor in an enchanting table.
 * @property soundEvent The sound this armor makes.
 * @property toughness How much this armor protects against powerful hits.
 * @property repairMaterial What item is used to repair this armor.
 * @property knockbackResistance How resistant this armor is to knockback.
 */
enum class RubyArmorMaterial(
	private val materialName: String, private val maxDamageFactor: Int,
	private val damageReductionAmount: IntArray, private val enchantability: Int,
	private val soundEvent: SoundEvent, private val toughness: Float,
	private val repairMaterial: Lazy<Ingredient>, private val knockbackResistance: Float,
) : IArmorMaterial {
	RUBY(RubyMod.MOD_ID + ":ruby",
		24, intArrayOf(2, 5, 6, 2),
		18,
		SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
		0f,
		Lazy.of { Ingredient.fromItems(ItemRegistry.RUBY) },
		0.5f);

	override fun getDurability(slotIn: EquipmentSlotType): Int {
		return MAX_DAMAGE[slotIn.index] * maxDamageFactor
	}

	override fun getDamageReductionAmount(slotIn: EquipmentSlotType): Int {
		return damageReductionAmount[slotIn.index]
	}

	override fun getEnchantability(): Int {
		return enchantability
	}

	@Nonnull
	override fun getSoundEvent(): SoundEvent {
		return soundEvent
	}

	@Nonnull
	override fun getRepairMaterial(): Ingredient {
		return repairMaterial.get()
	}

	override fun getToughness(): Float {
		return toughness
	}

	override fun getName(): String {
		return materialName
	}

	override fun getKnockbackResistance(): Float {
		return knockbackResistance
	}

	companion object {
		private val MAX_DAMAGE = intArrayOf(11, 16, 15, 13)
	}
}