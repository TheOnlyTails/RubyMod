package com.github.theonlytails.rubymod.datagen.modeltype

import com.github.theonlytails.rubymod.datagen.ModelsGenerator
import net.minecraft.item.Item
import net.minecraftforge.client.model.generators.ModelFile

class SpawnEggModelType : ItemModelType() {
	/**
	 * Generate a model for the item.
	 */
	override fun apply(item: Item, gen: ModelsGenerator) {
		spawnEggItem(item, gen)
	}

	private fun spawnEggItem(item: Item, gen: ModelsGenerator) =
		spawnEggItem(item.registryName!!.path, gen)

	private fun spawnEggItem(path: String, gen: ModelsGenerator) =
		gen.itemModels().getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(gen.mcLoc("item/template_spawn_egg")))
}
