package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator

@Suppress("LeakingThis")
abstract class ModelType<T> {
	init {
		ModelsGenerator.MODEL_TYPES.add(this)
	}

	abstract fun generateModels(generator: ModelsGenerator)

	companion object {
		// Block models
		val SIMPLE_BLOCK = SimpleBlockModelType()
		val SLAB_BLOCK = SlabBlockModelType()
		val STAIRS_BLOCK = StairsModelType()

		// Item models
		val SIMPLE_ITEM = SimpleItemModelType()
		val HANDHELD_ITEM = HandheldItemModelType()
		val SPAWN_EGG_ITEM = SpawnEggModelType()
	}
}
