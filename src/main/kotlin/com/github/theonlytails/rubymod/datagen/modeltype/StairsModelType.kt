package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.block.Block
import net.minecraft.block.StairsBlock

class StairsModelType : ModelType<StairsBlock>() {
	private val map = HashMap<StairsBlock, Block>()

	override fun generateModels(generator: ModelsGenerator) {
		map.forEach { (stairs, block) -> stairs(stairs, block, generator) }
	}

	private fun stairs(stairs: Block, block: Block, gen: ModelsGenerator) {
		if (stairs is StairsBlock) {
			gen.stairsBlock(stairs, gen.modLoc("blocks/" + block.registryName!!.path))
			gen.blockItemModel(stairs)
		} else {
			throw IllegalArgumentException("Not a stair")
		}
	}
}