package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.block.Block

abstract class BlockModelType<B : Block> : ModelType<B>() {
	val blocks = arrayListOf<B>()

	fun add(t: B) = blocks.add(t)

	override fun generateModels(generator: ModelsGenerator) = blocks.forEach { process(it, generator) }

	abstract fun process(block: B, generator: ModelsGenerator)
}