package com.github.theonlytails.rubymod.events

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.ItemRegistry
import com.github.theonlytails.rubymod.registries.PotionRegistry
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionBrewing
import net.minecraft.potion.Potions
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object ModEventBusSubscriber {
	@SubscribeEvent
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