package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.logger
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.advancements.criterion.EnchantmentPredicate
import net.minecraft.advancements.criterion.ItemPredicate
import net.minecraft.advancements.criterion.MinMaxBounds
import net.minecraft.advancements.criterion.StatePropertiesPredicate
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock
import net.minecraft.data.DataGenerator
import net.minecraft.data.DirectoryCache
import net.minecraft.data.IDataProvider
import net.minecraft.data.LootTableProvider
import net.minecraft.enchantment.Enchantments
import net.minecraft.loot.*
import net.minecraft.loot.conditions.BlockStateProperty
import net.minecraft.loot.conditions.MatchTool
import net.minecraft.loot.conditions.SurvivesExplosion
import net.minecraft.loot.functions.ApplyBonus
import net.minecraft.loot.functions.CopyName
import net.minecraft.loot.functions.ExplosionDecay
import net.minecraft.loot.functions.SetCount
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
        loot.addLoot(
            BlockRegistry.centrifuge, LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(
                            ItemLootEntry.lootTableItem(ItemRegistry.centrifuge)
                                .`when`(SILK_TOUCH)
                                .otherwise(ItemLootEntry.lootTableItem(ItemRegistry.ruby))
                        )
                        .`when`(SurvivesExplosion.survivesExplosion())
                )
        )

        // Ruby Barrel
        loot.addLoot(
            BlockRegistry.rubyBarrel, LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(
                            ItemLootEntry.lootTableItem(ItemRegistry.rubyBarrel)
                                .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
                        )
                        .`when`(SurvivesExplosion.survivesExplosion())
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
        loot.addLoot(
            BlockRegistry.rubyOre, LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(
                            ItemLootEntry.lootTableItem(ItemRegistry.rubyOre)
                                .`when`(SILK_TOUCH)
                                .otherwise(
                                    ItemLootEntry.lootTableItem(ItemRegistry.ruby)
                                        .apply(ExplosionDecay.explosionDecay())
                                        .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                )
                        )
                        .`when`(SurvivesExplosion.survivesExplosion())
                )
        )

        // Ruby Wool
        loot.dropSelf(BlockRegistry.rubyWool)
    }

    // Always drop, unless explosion
    private fun dropSelf(block: Block) {
        val pool = LootPool.lootPool()
            .setRolls(ConstantRange.exactly(1))
            .add(ItemLootEntry.lootTableItem(block))
            .`when`(SurvivesExplosion.survivesExplosion())

        tables[block] = LootTable.lootTable().withPool(pool)
    }

    private fun dropSlabs(block: Block) {
        val pool = LootPool.lootPool()
            .setRolls(ConstantRange.exactly(1))
            .add(ItemLootEntry.lootTableItem(block))
            .`when`(SurvivesExplosion.survivesExplosion())
            .apply(
                SetCount.setCount(ConstantRange.exactly(2))
                    .`when`(
                        BlockStateProperty.hasBlockStateProperties(block)
                            .setProperties(
                                StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)
                            )
                    )
            )

        tables[block] = LootTable.lootTable().withPool(pool)
    }

    // Add a custom loot table
    private fun addLoot(block: Block, loot: LootTable.Builder) {
        tables[block] = loot
    }

    /**
     * Performs this provider's action.
     */
    override fun run(cache: DirectoryCache) {
        addLootTables(this)

        val namespacedTables = hashMapOf<ResourceLocation, LootTable>()

        for (entry in tables) {
            namespacedTables[entry.key.lootTable] = entry.value.setParamSet(LootParameterSets.BLOCK).build()
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

    companion object {
        // Templates
        private val SILK_TOUCH = MatchTool.toolMatches(
            ItemPredicate.Builder.item()
                .hasEnchantment(EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))
        )
    }
}
