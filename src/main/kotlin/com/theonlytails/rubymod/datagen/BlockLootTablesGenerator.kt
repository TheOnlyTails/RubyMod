package com.theonlytails.rubymod.datagen

import com.theonlytails.loottables.*
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.LootTableProvider
import net.minecraft.enchantment.Enchantments
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
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
						itemEntry(ItemRegistry.centrifuge, addToPool = false) {
							condition { hasSilkTouch }
						},
						itemEntry(ItemRegistry.ruby, addToPool = false),
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
					itemEntry(ItemRegistry.rubyOre, addToPool = false) {
						condition { hasSilkTouch }
					},
					itemEntry(ItemRegistry.ruby, addToPool = false) {
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
					setConstantCount(2) {
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

		writeLootTables(generator, namespacedTables, cache)
	}
}
