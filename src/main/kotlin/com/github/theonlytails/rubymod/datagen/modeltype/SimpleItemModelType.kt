package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.item.Item
import net.minecraftforge.client.model.generators.ModelFile

class SimpleItemModelType : ItemModelType() {
	/**
	 * Generate a model for the item.
	 */
	override fun apply(item: Item, gen: ModelsGenerator) {
		simpleItem(item, gen)
	}

	/**
	 * Generates a 2d in-gui model with a single texture.
	 * Think of the lantern or the hopper
	 */
	private fun simpleItem(item: Item, gen: ModelsGenerator) = simpleItem(item.registryName!!.path, gen)

	private fun simpleItem(path: String, gen: ModelsGenerator) =
		gen.itemModels().getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(gen.mcLoc("item/generated")))
			.texture("layer0", gen.modLoc("items/$path"))
}
