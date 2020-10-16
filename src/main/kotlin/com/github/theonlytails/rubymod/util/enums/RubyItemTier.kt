package com.github.theonlytails.rubymod.util.enums

import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.item.IItemTier
import net.minecraft.item.crafting.Ingredient
import java.util.function.Supplier
import javax.annotation.Nonnull

enum class RubyItemTier(
	private val harvestLevel: Int, private val maxUses: Int, private val efficiency: Float,
	private val attackDamage: Float, private val enchantability: Int,
	private val repairMaterial: Supplier<Ingredient>,
) : IItemTier {
	RUBY(3,
		850,
		7f,
		3f,
		12,
		Supplier<Ingredient> { Ingredient.fromItems(ItemRegistry.RUBY) });

	override fun getMaxUses(): Int {
		return maxUses
	}

	override fun getEfficiency(): Float {
		return efficiency
	}

	override fun getAttackDamage(): Float {
		return attackDamage
	}

	override fun getHarvestLevel(): Int {
		return harvestLevel
	}

	override fun getEnchantability(): Int {
		return enchantability
	}

	@Nonnull
	override fun getRepairMaterial(): Ingredient {
		return repairMaterial.get()
	}
}