package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.ModelFile

class HandheldItemModelType : ItemModelType() {
	/**
	 * Generate a model for the item.
	 */
	override fun apply(item: Item, gen: ModelsGenerator) {
		handheldItem(item, gen)
	}

	private fun handheldItem(item: Item, gen: ModelsGenerator) = handheldItem(item.registryName!!.path, gen)

	private fun handheldItem(path: String, gen: ModelsGenerator) =
		singleLayerItem(gen, path, gen.mcLoc("item/handheld"))

	private fun singleLayerItem(gen: ModelsGenerator, path: String, parent: ResourceLocation) =
		gen.itemModels().getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(parent))
			.texture("layer0", gen.modLoc("items/$path"))
}
