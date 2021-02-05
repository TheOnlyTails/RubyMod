package com.theonlytails.rubymod.datagen

import com.google.gson.GsonBuilder
import net.minecraft.data.*
import net.minecraft.item.Items
import net.minecraft.loot.*
import net.minecraft.util.ResourceLocation as RL

/**
 * Generates loot tables for hero of the village gifts.
 *
 * @author TheOnlyTails
 */
class GiftLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val jewelerGiftLootTable = com.theonlytails.rubymod.RubyMod.id("gameplay/hero_of_the_village/jeweler_gift")

	private val tables = hashMapOf<RL, LootTable>(
		jewelerGiftLootTable to
				LootTable.builder().addLootPool(LootPool.builder()
					.rolls(ConstantRange(1))
					.addEntry(ItemLootEntry.builder(Items.IRON_NUGGET))
					.addEntry(ItemLootEntry.builder(Items.IRON_INGOT))
					.addEntry(ItemLootEntry.builder(Items.GOLD_NUGGET))
					.addEntry(ItemLootEntry.builder(Items.GOLD_INGOT))
				).setParameterSet(LootParameterSets.GIFT).build()
	)

	/**
	 * Performs this provider's action.
	 */
	override fun act(cache: DirectoryCache) {
		writeLootTables(tables, cache)
	}

	private fun writeLootTables(tables: HashMap<RL, LootTable>, cache: DirectoryCache) {
		val output = generator.outputFolder

		tables.forEach { (key, table) ->
			val path =
				output.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(table), path)
			} catch (e: Exception) {
				com.theonlytails.rubymod.RubyMod.LOGGER.error("Couldn't write loot table $path", e)
			}
		}
	}

	companion object {
		// Internal stuff
		private val GSON = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
	}
}
