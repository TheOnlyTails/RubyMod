package com.theonlytails.rubymod.util

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
fun registerBrewingRecipes(event: FMLCommonSetupEvent) {
	PotionBrewing.addMix(Potions.WATER, ItemRegistry.ruby, PotionRegistry.motivation)

	addPotency(PotionRegistry.motivation, PotionRegistry.strongMotivation)
	addTime(PotionRegistry.motivation, PotionRegistry.longMotivation)

	addInverted(PotionRegistry.motivation, PotionRegistry.laziness)
	addPotency(PotionRegistry.laziness, PotionRegistry.strongLaziness)
	addTime(PotionRegistry.laziness, PotionRegistry.longLaziness)
}

fun addPotency(input: Potion, output: Potion) = PotionBrewing.addMix(input, Items.GLOWSTONE_DUST, output)

fun addTime(input: Potion, output: Potion) = PotionBrewing.addMix(input, Items.REDSTONE, output)

fun addInverted(input: Potion, output: Potion) = PotionBrewing.addMix(input, Items.FERMENTED_SPIDER_EYE, output)
