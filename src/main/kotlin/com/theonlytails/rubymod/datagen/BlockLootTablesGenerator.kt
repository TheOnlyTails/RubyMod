package com.theonlytails.rubymod.datagen

import com.google.gson.GsonBuilder
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.advancements.criterion.*
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.data.*
import net.minecraft.enchantment.Enchantments
import net.minecraft.loot.*
import net.minecraft.loot.conditions.BlockStateProperty
import net.minecraft.loot.conditions.MatchTool
import net.minecraft.loot.conditions.SurvivesExplosion
import net.minecraft.loot.functions.*
import net.minecraft.state.properties.SlabType
import net.minecraft.util.ResourceLocation

/**
 * Generates loot tables for blocks.
 *
 * @author TheOnlyTails
 */
class BlockLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val tables = hashMapOf<Block, LootTable.Builder>()

	private fun addLootTables(loot: BlockLootTablesGenerator) {
		// Centrifuge
		loot.addLoot(BlockRegistry.CENTRIFUGE_BLOCK, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.CENTRIFUGE_BLOCK_ITEM)
						.acceptCondition(SILK_TOUCH)
						.alternatively(ItemLootEntry.builder(ItemRegistry.RUBY))
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Barrel
		loot.addLoot(BlockRegistry.RUBY_BARREL, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.RUBY_BARREL_ITEM)
						.acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Block
		loot.dropSelf(BlockRegistry.RUBY_BLOCK)

		// Ruby Slab
		loot.dropSlabs(BlockRegistry.RUBY_SLAB)

		// Ruby Stairs
		loot.dropSelf(BlockRegistry.RUBY_STAIRS)

		// Ruby Pressure Plate
		loot.dropSelf(BlockRegistry.RUBY_PRESSURE_PLATE)

		// Ruby Button
		loot.dropSelf(BlockRegistry.RUBY_BUTTON)

		// Ruby Wall
		loot.dropSelf(BlockRegistry.RUBY_WALL)

		// Ruby Carpet
		loot.dropSelf(BlockRegistry.RUBY_CARPET)

		// Logic Gate
		loot.dropSelf(BlockRegistry.LOGIC_GATE)

		// Ruby Ore
		loot.addLoot(BlockRegistry.RUBY_ORE_BLOCK, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.RUBY_ORE_BLOCK_ITEM)
						.acceptCondition(SILK_TOUCH)
						.alternatively(
							ItemLootEntry.builder(ItemRegistry.RUBY)
								.acceptFunction(ExplosionDecay.builder())
								.acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
						)
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Wool
		loot.dropSelf(BlockRegistry.RUBY_WOOL)
	}

	// Always drop, unless explosion
	private fun dropSelf(block: Block) {
		val pool = LootPool.builder()
			.rolls(ConstantRange.of(1))
			.addEntry(ItemLootEntry.builder(block))
			.acceptCondition(SurvivesExplosion.builder())

		tables[block] = LootTable.builder().addLootPool(pool)
	}

	private fun dropSlabs(block: Block) {
		val pool = LootPool.builder()
			.rolls(ConstantRange.of(1))
			.addEntry(ItemLootEntry.builder(block))
			.acceptCondition(SurvivesExplosion.builder())
			.acceptFunction(
				SetCount.builder(ConstantRange.of(2))
					.acceptCondition(
						BlockStateProperty.builder(block)
							.fromProperties(
								StatePropertiesPredicate.Builder.newBuilder()
									.withProp(SlabBlock.TYPE, SlabType.DOUBLE)
							)
					)
			)

		tables[block] = LootTable.builder().addLootPool(pool)
	}

	// Add a custom loot table
	private fun addLoot(block: Block, loot: LootTable.Builder) {
		tables[block] = loot
	}

	/**
	 * Performs this provider's action.
	 */
	override fun act(cache: DirectoryCache) {
		addLootTables(this)

		val namespacedTables = hashMapOf<ResourceLocation, LootTable>()

		for (entry in tables) {
			namespacedTables[entry.key.lootTable] = entry.value.setParameterSet(LootParameterSets.BLOCK).build()
		}

		writeLootTables(namespacedTables, cache)
	}

	private fun writeLootTables(tables: Map<ResourceLocation, LootTable>, cache: DirectoryCache) {
		val output = generator.outputFolder

		tables.forEach { (key, table) ->
			val path = output.resolve("data/${key.namespace}/loot_tables/${key.path}.json")

			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(table), path)
			} catch (e: Exception) {
				com.theonlytails.rubymod.RubyMod.LOGGER.error("Couldn't write loot table $path", e)
			}
		}
	}

	companion object {
		// Templates
		private val SILK_TOUCH = MatchTool.builder(
			ItemPredicate.Builder.create()
				.enchantment(EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))

		// Internal stuff
		private val GSON = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
	}
}