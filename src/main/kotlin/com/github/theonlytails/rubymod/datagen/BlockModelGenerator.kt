package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.block.SlabBlock
import net.minecraft.data.DataGenerator
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class BlockModelGenerator(
	gen: DataGenerator,
	helper: ExistingFileHelper,
) : BlockStateProvider(gen, RubyMod.MOD_ID, helper) {
	override fun registerStatesAndModels() {
		simpleBlock(BlockRegistry.RUBY_BLOCK)
		simpleBlock(BlockRegistry.RUBY_ORE_BLOCK)
		simpleBlock(BlockRegistry.RUBY_WOOL)
		slab(BlockRegistry.RUBY_SLAB, modLoc("blocks/ruby_block"))
		stairsBlock(BlockRegistry.RUBY_STAIRS, modLoc("blocks/ruby_block"))
	}

	private fun slab(block: SlabBlock, a: ResourceLocation) =
		slabBlock(block, a, a)
}