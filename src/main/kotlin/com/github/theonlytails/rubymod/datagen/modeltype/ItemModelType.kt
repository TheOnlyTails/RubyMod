package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.item.Item
import net.minecraft.util.IItemProvider
import java.util.ArrayList

abstract class ItemModelType : ModelType<Item>() {
	private val items = ArrayList<Item>()

	override fun generateModels(generator: ModelsGenerator) {
		items.forEach { apply(it, generator) }
	}

	/**
	 * Generate a model for the item.
	 */
	abstract fun apply(item: Item, gen: ModelsGenerator)

	fun add(item: IItemProvider) = items.add(item.asItem())
}