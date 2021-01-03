package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.RubyMod.id
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class ItemModelsGenerator(generator: DataGenerator, helper: ExistingFileHelper) :
	ItemModelProvider(generator, RubyMod.MOD_ID, helper) {

	override fun registerModels() {
		// ruby
		getBuilder("ruby")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ruby"))

		// ghost water bucket
		getBuilder("ghost_water_bucket")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ghost_water_bucket"))

		// poisoned apple
		getBuilder("poisoned_apple")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/poisoned_apple"))

		// ruby sheep spawn egg
		getBuilder("ruby_sheep_spawn_egg")
			.parent(ModelFile.UncheckedModelFile("item/template_spawn_egg"))

		generateToolModels()
		generateArmorModels()
		generateBlockItemModels()
	}

	private fun generateBlockItemModels() {
		// centrifuge
		getBuilder("centrifuge")
			.parent(ModelFile.UncheckedModelFile(id("block/centrifuge")))

		// logic gate
		getBuilder("logic_gate")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/logic_gate"))

		// centrifuge
		getBuilder("ruby_barrel")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_barrel")))

		// ruby block
		getBuilder("ruby_block")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_block")))

		// ruby button
		getBuilder("ruby_button")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_button_inventory")))

		// ruby carpet
		getBuilder("ruby_carpet")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_carpet")))

		// ruby ore
		getBuilder("ruby_ore")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_ore")))

		// ruby pressure plate
		getBuilder("ruby_pressure_plate")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_pressure_plate")))

		// ruby slab
		getBuilder("ruby_slab")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_slab")))

		// ruby stairs
		getBuilder("ruby_stairs")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_stairs")))

		// ruby wall
		getBuilder("ruby_wall")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_wall_inventory")))

		// ruby wool
		getBuilder("ruby_wool")
			.parent(ModelFile.UncheckedModelFile(id("block/ruby_wool")))
	}

	private fun generateToolModels() {
		getBuilder("ruby_pickaxe")
			.parent(ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", id("items/ruby_pickaxe"))

		getBuilder("ruby_sword")
			.parent(ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", id("items/ruby_sword"))

		getBuilder("ruby_axe")
			.parent(ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", id("items/ruby_axe"))

		getBuilder("ruby_shovel")
			.parent(ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", id("items/ruby_shovel"))

		getBuilder("ruby_hoe")
			.parent(ModelFile.UncheckedModelFile("item/handheld"))
			.texture("layer0", id("items/ruby_hoe"))
	}

	private fun generateArmorModels() {
		getBuilder("ruby_boots")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ruby_boots"))

		getBuilder("ruby_chestplate")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ruby_chestplate"))

		getBuilder("ruby_leggings")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ruby_leggings"))

		getBuilder("ruby_helmet")
			.parent(ModelFile.UncheckedModelFile("item/generated"))
			.texture("layer0", id("items/ruby_helmet"))
	}
}
