package com.theonlytails.rubymod.events

import com.theonlytails.rubymod.registries.ItemRegistry
import com.theonlytails.rubymod.registries.PotionRegistry
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionBrewing
import net.minecraft.potion.Potions
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

/**
 * Holds generic events that run on the mod bus.
 *
 * @author TheOnlyTails
 */

object ModEvents {
	@Suppress("UNUSED_PARAMETER")
	fun registerBrewingRecipes(event: FMLCommonSetupEvent) {
		PotionBrewing.addMix(
			Potions.WATER,
			ItemRegistry.RUBY,
			PotionRegistry.MOTIVATION)

		addPotency(PotionRegistry.MOTIVATION, PotionRegistry.STRONG_MOTIVATION)
		addTime(PotionRegistry.MOTIVATION, PotionRegistry.LONG_MOTIVATION)

		addInverted(PotionRegistry.MOTIVATION, PotionRegistry.LAZINESS)
		addPotency(PotionRegistry.LAZINESS, PotionRegistry.STRONG_LAZINESS)
		addTime(PotionRegistry.LAZINESS, PotionRegistry.LONG_LAZINESS)
	}

	private fun addPotency(input: Potion, output: Potion) {
		PotionBrewing.addMix(input, Items.GLOWSTONE_DUST, output)
	}

	private fun addTime(input: Potion, output: Potion) {
		PotionBrewing.addMix(input, Items.REDSTONE, output)
	}

	private fun addInverted(input: Potion, output: Potion) {
		PotionBrewing.addMix(input, Items.FERMENTED_SPIDER_EYE, output)
	}
}