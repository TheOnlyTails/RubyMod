package com.github.theonlytails.rubymod.datagen

import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val helper = event.existingFileHelper

		if (event.includeClient()) {
			generator.addProvider(Lang.English(generator))
		}

		if (event.includeServer()) {
			val blockTags = BlockTagDataGenerator(generator, helper)

			generator.addProvider(RecipesGenerator(generator))
			generator.addProvider(BlockLootTablesGenerator(generator))
			generator.addProvider(EntityLootTablesGenerator(generator))
			generator.addProvider(GiftLootTablesGenerator(generator))
			generator.addProvider(blockTags)
			generator.addProvider(ItemTagGenerator(generator, blockTags, helper))
		}
	}
}