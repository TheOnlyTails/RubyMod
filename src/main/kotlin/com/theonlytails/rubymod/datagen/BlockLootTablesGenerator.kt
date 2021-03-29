package com.theonlytails.rubymod.datagen

import com.theonlytails.loottables.*
import com.theonlytails.rubymod.logger
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.data.*
import net.minecraft.enchantment.Enchantments
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTableManager
import net.minecraft.loot.functions.CopyName
import net.minecraft.state.properties.SlabType
import net.minecraft.util.ResourceLocation

/**
 * Generates loot tables for blocks.
 *
 * @author TheOnlyTails
 */
class BlockLootTablesGenerator(private val generator: DataGenerator) : LootTableProvider(generator) {
	private val tables = hashMapOf<Block, LootTable>()

	private fun addLootTables(loot: BlockLootTablesGenerator) {
		// Centrifuge
		loot.addLoot(
			BlockRegistry.centrifuge, lootTable(LootParameterSets.BLOCK) {
				pool {
					alternativesEntry(
						itemEntry(ItemRegistry.centrifuge) {
							condition { hasSilkTouch() }
						},
						itemEntry(ItemRegistry.ruby),
					)

					condition { survivesExplosion() }
				}
			}
		)

		// Ruby Barrel
		loot.addLoot(BlockRegistry.rubyBarrel, lootTable(LootParameterSets.BLOCK) {
			pool {
				itemEntry(BlockRegistry.rubyBarrel) {
					function { copyName(CopyName.Source.BLOCK_ENTITY) }
				}

				condition { survivesExplosion() }
			}
		})

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
		loot.addLoot(BlockRegistry.rubyOre, lootTable(LootParameterSets.BLOCK) {
			pool {
				alternativesEntry(
					itemEntry(ItemRegistry.rubyOre) {
						condition { hasSilkTouch() }
					},
					itemEntry(ItemRegistry.ruby) {
						function { explosionDecay() }
						function { oreBonusCount(Enchantments.BLOCK_FORTUNE) }
					},
				)

				condition { survivesExplosion() }
			}
		})

		// Ruby Wool
		loot.dropSelf(BlockRegistry.rubyWool)
	}

	// Always drop, unless explosion
	private fun dropSelf(block: Block) {
		tables[block] = lootTable(LootParameterSets.BLOCK) {
			pool {
				itemEntry(block)
				condition { survivesExplosion() }
			}
		}
	}

	private fun dropSlabs(block: Block) {
		tables[block] = lootTable(LootParameterSets.BLOCK) {
			pool {
				itemEntry(block)

				condition { survivesExplosion() }

				function {
					setCount(constantRange(2)) {
						condition {
							blockStateProperty(block) {
								setProperties(stateProperties { hasProperty(SlabBlock.TYPE, SlabType.DOUBLE) })
							}
						}
					}
				}
			}
		}
	}

	// Add a custom loot table
	private fun addLoot(block: Block, loot: LootTable) {
		tables[block] = loot
	}

	/**
	 * Performs this provider's action.
	 */
	override fun run(cache: DirectoryCache) {
		addLootTables(this)

		val namespacedTables = hashMapOf<ResourceLocation, LootTable>()

		for (entry in tables) {
			namespacedTables[entry.key.lootTable] = entry.value
		}

		writeLootTables(namespacedTables, cache)
	}

	private fun writeLootTables(tables: Map<ResourceLocation, LootTable>, cache: DirectoryCache) {
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
}
