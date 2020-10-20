package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.EntityTypeRegistry
import com.github.theonlytails.rubymod.registries.ItemRegistry
import com.google.gson.GsonBuilder
import net.minecraft.data.*
import net.minecraft.data.loot.EntityLootTables
import net.minecraft.entity.EntityType
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTableManager
import net.minecraft.util.ResourceLocation
import java.util.HashMap

class EntityLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val tables = hashMapOf<EntityType<*>, LootTable.Builder>()

	private fun addLootTables(loot: EntityLootTablesGenerator) {
		// Ruby Sheep
		loot.addLoot(
			EntityTypeRegistry.RUBY_SHEEP,
			EntityLootTables.sheepLootTableBuilderWithDrop(ItemRegistry.RUBY_WOOL_ITEM),
		)
	}

	/**
	 * Performs this provider's action.
	 */
	override fun act(cache: DirectoryCache) {
		addLootTables(this)

		val namespacedTables = hashMapOf<ResourceLocation, LootTable>()

		for (entry in tables) {
			namespacedTables[entry.key.lootTable] = entry.value.setParameterSet(LootParameterSets.ENTITY).build()
		}

		writeLootTables(namespacedTables, cache)
	}

	private fun writeLootTables(tables: HashMap<ResourceLocation, LootTable>, cache: DirectoryCache) {
		val output = generator.outputFolder

		tables.forEach { (key, table) ->
			val path = output.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(table), path)
			} catch (e: Exception) {
				RubyMod.LOGGER.error("Couldn't write loot table $path", e)
			}
		}
	}

	private fun addLoot(entityType: EntityType<*>, loot: LootTable.Builder) {
		tables[entityType] = loot
	}

	companion object {
		// Internal stuff
		private val GSON = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
	}
}