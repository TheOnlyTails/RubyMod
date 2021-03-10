package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.logger
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.IDataProvider
import net.minecraft.data.LootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.*
import net.minecraft.util.ResourceLocation as RL

/**
 * Generates loot tables for hero of the village gifts.
 *
 * @author TheOnlyTails
 */
class GiftLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val jewelerGiftLootTable = id("gameplay/hero_of_the_village/jeweler_gift")

	private val tables = hashMapOf<RL, LootTable>(
		jewelerGiftLootTable to
				LootTable.lootTable().withPool(
					LootPool.lootPool()
						.setRolls(ConstantRange.exactly(1))
						.add(ItemLootEntry.lootTableItem(Items.IRON_NUGGET))
						.add(ItemLootEntry.lootTableItem(Items.IRON_INGOT))
						.add(ItemLootEntry.lootTableItem(Items.GOLD_NUGGET))
						.add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT))
				).setParamSet(LootParameterSets.GIFT).build()
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
