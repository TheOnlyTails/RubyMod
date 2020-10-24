package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.Block
import net.minecraft.data.DataGenerator
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class ItemModelGenerator(
	gen: DataGenerator,
	helper: ExistingFileHelper,
) : ItemModelProvider(gen, RubyMod.MOD_ID, helper) {

	override fun registerModels() {
		// Items
		simpleItem(ItemRegistry.RUBY)
		simpleItem(ItemRegistry.RUBY_BOOTS)
		simpleItem(ItemRegistry.RUBY_LEGGINGS)
		simpleItem(ItemRegistry.RUBY_CHESTPLATE)
		simpleItem(ItemRegistry.RUBY_HELMET)
		handHeldItem(ItemRegistry.RUBY_PICKAXE)
		handHeldItem(ItemRegistry.RUBY_SWORD)
		handHeldItem(ItemRegistry.RUBY_AXE)
		handHeldItem(ItemRegistry.RUBY_SHOVEL)
		handHeldItem(ItemRegistry.RUBY_HOE)
		simpleItem(ItemRegistry.RUBY_WATER_BUCKET)
		simpleItem(ItemRegistry.POISONED_APPLE)
		spawnEggItem(ItemRegistry.RUBY_SHEEP_SPAWN_EGG)

		// Blocks
		blockItemModel(BlockRegistry.RUBY_BLOCK)
		blockItemModel(BlockRegistry.RUBY_SLAB)
		blockItemModel(BlockRegistry.RUBY_STAIRS)
		blockItemModel(BlockRegistry.RUBY_ORE_BLOCK)
		blockItemModel(BlockRegistry.RUBY_WOOL)
		blockItemModel(BlockRegistry.RUBY_CARPET)
	}

	private fun simpleItem(item: Item) = singleLayerItem(item.registryName!!.path, mcLoc("item/generated"))

	/**
	 * Generates an in-gui version of the block
	 * that appears the same way it does in the world.
	 */
	private fun blockItemModel(block: Block) {
		val name = block.registryName!!.path
		blockItemModel(name, name)
	}

	/**
	 * Generates an in-gui version for the
	 * block with the specified parent model.
	 */
	private fun blockItemModel(path: String, parent: String) {
		getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(modLoc("block/$parent")))
	}

	private fun handHeldItem(item: Item) =
		handHeldItem(item.registryName!!.path)

	private fun handHeldItem(path: String) =
		singleLayerItem(path, mcLoc("item/handheld"))

	private fun spawnEggItem(item: Item) =
		getBuilder(item.registryName!!.path)
			.parent(ModelFile.UncheckedModelFile(mcLoc("item/template_spawn_egg")))

	private fun singleLayerItem(path: String, parent: ResourceLocation) =
		getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(parent))
			.texture("layer0", modLoc("items/$path"))
}