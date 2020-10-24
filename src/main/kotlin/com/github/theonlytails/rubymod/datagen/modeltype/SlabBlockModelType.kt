package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.block.Block
import net.minecraft.block.SlabBlock

class SlabBlockModelType : ModelType<SlabBlock>() {
	private val blockToSlabs = HashMap<SlabBlock, Block>()

	override fun generateModels(generator: ModelsGenerator) {
		blockToSlabs.forEach { (slab, block) ->
			slab(generator, slab, block)
		}
	}

	fun slab(generator: ModelsGenerator, slab: SlabBlock, block: Block) {
		val loc = generator.modLoc("blocks/${block.registryName!!.path}")
		generator.slabBlock(slab, loc, loc)
	}

	fun add(slab: SlabBlock, block: Block) {
		blockToSlabs[slab] = block
	}
}
