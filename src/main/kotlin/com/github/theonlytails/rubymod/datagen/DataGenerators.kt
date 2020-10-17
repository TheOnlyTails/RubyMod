package com.github.theonlytails.rubymod.datagen

import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator = event.generator

		if (event.includeServer()) {
			val blockTags = BlockTagDataGenerator(generator)

			generator.addProvider(RecipesDataGenerator(generator))
			generator.addProvider(BlockLootTablesDataGenerator(generator))
			generator.addProvider(EntityLootTablesDataGenerator(generator))
			generator.addProvider(blockTags)
			generator.addProvider(ItemTagDataGenerator(generator, blockTags))
		}
	}
}