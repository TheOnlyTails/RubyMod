package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.logger
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.IDataProvider
import net.minecraft.data.LootTableProvider
import net.minecraft.data.loot.EntityLootTables
import net.minecraft.entity.EntityType
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTableManager
import net.minecraft.util.ResourceLocation

/**
 * Generates loot tables for entities.
 *
 * @author TheOnlyTails
 */
class EntityLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val tables = hashMapOf<EntityType<*>, LootTable.Builder>()

	private fun addLootTables(loot: EntityLootTablesGenerator) {
		// Ruby Sheep
		loot.addLoot(
			EntityTypeRegistry.rubySheep,
			EntityLootTables.createSheepTable { ItemRegistry.rubyWool },
		)
	}

	/**
	 * Performs this provider's action.
	 */
	override fun run(cache: DirectoryCache) {
		addLootTables(this)

		val namespacedTables = hashMapOf<ResourceLocation, LootTable>()

		for (entry in tables) {
			namespacedTables[entry.key.defaultLootTable] = entry.value.setParamSet(LootParameterSets.ENTITY).build()
		}

		writeLootTables(namespacedTables, cache)
	}

	private fun writeLootTables(tables: HashMap<ResourceLocation, LootTable>, cache: DirectoryCache) {
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

	private fun addLoot(entityType: EntityType<*>, loot: LootTable.Builder) {
		tables[entityType] = loot
	}
}
