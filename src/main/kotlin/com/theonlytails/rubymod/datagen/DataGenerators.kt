package com.theonlytails.rubymod.datagen

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.theonlytails.rubymod.logger
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.IDataProvider
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTableManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent

val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

/**
 * Calls all of the data generators to regenerate their files.
 *
 * @author TheOnlyTails
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

	@SubscribeEvent
	fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val helper = event.existingFileHelper

		if (event.includeClient()) {
			generator.addProvider(LangGenerator.English(generator))
		}

		if (event.includeServer()) {
			val blockTags = BlockTagDataGenerator(generator, helper)
			val itemModels = ItemModelsGenerator(generator, helper)

			generator.addProvider(RecipesGenerator(generator))
			generator.addProvider(BlockLootTablesGenerator(generator))
			generator.addProvider(EntityLootTablesGenerator(generator))
			generator.addProvider(GiftLootTablesGenerator(generator))
			generator.addProvider(blockTags)
			generator.addProvider(ItemTagGenerator(generator, blockTags, helper))
			generator.addProvider(itemModels)
		}
	}
}

fun writeLootTables(generator: DataGenerator, tables: Map<ResourceLocation, LootTable>, cache: DirectoryCache) {
	val output = generator.outputFolder

	tables.forEach { (key, table) ->
		val path = output.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

		try {
			IDataProvider.save(gson, cache, LootTableManager.serialize(table), path)
		} catch (e: Exception) {
			logger.error("Couldn't write loot table $path", e)
		}
	}
}
