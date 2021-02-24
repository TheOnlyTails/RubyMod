package com.theonlytails.rubymod.datagen

import com.google.gson.GsonBuilder
import com.theonlytails.rubymod.RubyMod
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
		loot.addLoot(BlockRegistry.centrifuge, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.centrifuge)
						.acceptCondition(SILK_TOUCH)
						.alternatively(ItemLootEntry.builder(ItemRegistry.ruby))
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Barrel
		loot.addLoot(BlockRegistry.rubyBarrel, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.rubyBarrel)
						.acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Block
		loot.dropSelf(BlockRegistry.rubyBlock)

		// Ruby Slab
		loot.dropSlabs(BlockRegistry.rubySlab)

		// Ruby Stairs
		loot.dropSelf(BlockRegistry.rubyStairs)

		// Ruby Pressure Plate
		loot.dropSelf(BlockRegistry.rubyPressurePlate)

		// Ruby Button
		loot.dropSelf(BlockRegistry.rubyButton)

		// Ruby Wall
		loot.dropSelf(BlockRegistry.rubyWall)

		// Ruby Carpet
		loot.dropSelf(BlockRegistry.rubyCarpet)

		// Logic Gate
		loot.dropSelf(BlockRegistry.logicGate)

		// Ruby Ore
		loot.addLoot(BlockRegistry.rubyOre, LootTable.builder()
			.addLootPool(
				LootPool.builder()
					.addEntry(ItemLootEntry.builder(ItemRegistry.rubyOre)
						.acceptCondition(SILK_TOUCH)
						.alternatively(
							ItemLootEntry.builder(ItemRegistry.ruby)
								.acceptFunction(ExplosionDecay.builder())
								.acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
						)
					)
					.acceptCondition(SurvivesExplosion.builder())
			)
		)

		// Ruby Wool
		loot.dropSelf(BlockRegistry.rubyWool)
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
				RubyMod.LOGGER.error("Couldn't write loot table $path", e)
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
