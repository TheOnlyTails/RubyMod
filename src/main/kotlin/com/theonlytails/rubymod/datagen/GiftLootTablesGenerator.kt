package com.theonlytails.rubymod.datagen

import com.theonlytails.loottables.itemEntry
import com.theonlytails.loottables.lootTable
import com.theonlytails.loottables.pool
import com.theonlytails.rubymod.id
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.LootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootParameterSets

/**
 * Generates loot tables for hero of the village gifts.
 *
 * @author TheOnlyTails
 */
class GiftLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val jewelerGiftLootTable = id("gameplay/hero_of_the_village/jeweler_gift")

	private val tables = hashMapOf(
		jewelerGiftLootTable to lootTable(LootParameterSets.GIFT) {
			pool {
				itemEntry(Items.IRON_NUGGET)
				itemEntry(Items.IRON_INGOT)
				itemEntry(Items.GOLD_NUGGET)
				itemEntry(Items.GOLD_INGOT)
			}
		}
	)

	/**
	 * Performs this provider's action.
	 */
	override fun run(cache: DirectoryCache) {
		writeLootTables(generator, tables, cache)
	}
}
