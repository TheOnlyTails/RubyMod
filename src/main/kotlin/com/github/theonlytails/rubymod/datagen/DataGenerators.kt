package com.github.theonlytails.rubymod.datagen

import net.minecraft.data.DataGenerator
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator: DataGenerator = event.generator

		if (event.includeServer()) {
			generator.addProvider(RecipesDataGenerator(generator))
		}
	}
}