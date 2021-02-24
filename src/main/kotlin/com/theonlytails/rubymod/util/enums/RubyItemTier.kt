package com.theonlytails.rubymod.util.enums

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.item.IItemTier
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.common.util.Lazy

/**
 * Holds the properties of any ruby tool.
 *
 * @author TheOnlyTails
 *
 * @property harvestLevel Determines which blocks can or cannot be mined with a tool of this tier.
 * @property maxUses The base durability of a tool of this tier (adjusted later per tool).
 * @property efficiency How fast a tool of this tier can break blocks.
 * @property attackDamage How much damage a tool of this tier does when fully loaded.
 * @property enchantability Determines how good the enchantments will be when enchanting a tool of this tier in an enchanting table.
 * @property repairMaterial What item is used to repair tools of this tier.
 */
enum class RubyItemTier(
	private val harvestLevel: Int, private val maxUses: Int, private val efficiency: Float,
	private val attackDamage: Float, private val enchantability: Int,
	private val repairMaterial: Lazy<Ingredient>,
) : IItemTier {
	RUBY(3,
		850,
		7f,
		3f,
		12,
		Lazy.of { Ingredient.fromItems(ItemRegistry.ruby) });

	override fun getMaxUses() = maxUses

	override fun getEfficiency() = efficiency

	override fun getAttackDamage() = attackDamage

	override fun getHarvestLevel() = harvestLevel

	override fun getEnchantability() = enchantability

	override fun getRepairMaterial(): Ingredient = repairMaterial.get()
}
