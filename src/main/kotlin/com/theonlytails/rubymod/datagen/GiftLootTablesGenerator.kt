package com.theonlytails.rubymod.datagen

import com.theonlytails.loottables.*
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.logger
import net.minecraft.data.*
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTableManager
import net.minecraft.util.ResourceLocation as RL

/**
 * Generates loot tables for hero of the village gifts.
 *
 * @author TheOnlyTails
 */
class GiftLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val jewelerGiftLootTable = id("gameplay/hero_of_the_village/jeweler_gift")

	private val tables = hashMapOf(
		jewelerGiftLootTable to
				lootTable(LootParameterSets.GIFT) {
					pool {
						itemEntry(Items.IRON_NUGGET).add(this)
						itemEntry(Items.IRON_INGOT).add(this)
						itemEntry(Items.GOLD_NUGGET).add(this)
						itemEntry(Items.GOLD_INGOT).add(this)
					}
				}
	)

	/**
	 * Performs this provider's action.
	 */
	override fun run(cache: DirectoryCache) {
		writeLootTables(tables, cache)
	}

	private fun writeLootTables(tables: HashMap<RL, LootTable>, cache: DirectoryCache) {
		val output = generator.outputFolder

		tables.forEach { (key, table) ->
			val path =
				output.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

			try {
				IDataProvider.save(gson, cache, LootTableManager.serialize(table), path)
			} catch (e: Exception) {
				logger.error("Couldn't write loot table $path", e)
			}
		}
	}
}
