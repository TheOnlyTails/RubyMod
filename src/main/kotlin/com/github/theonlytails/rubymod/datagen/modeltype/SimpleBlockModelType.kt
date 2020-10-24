package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.block.Block

class SimpleBlockModelType : BlockModelType<Block>() {
	override fun process(block: Block, generator: ModelsGenerator) {
		generator.simpleBlock(block)
		generator.blockItemModel(block)
	}
}
