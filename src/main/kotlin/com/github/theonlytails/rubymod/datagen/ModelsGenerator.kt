package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.datagen.modeltype.ModelType
import net.minecraft.block.Block
import net.minecraft.block.StairsBlock
import net.minecraft.data.DataGenerator
import net.minecraft.util.IItemProvider
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.generators.BlockModelBuilder
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class ModelsGenerator(
	generator: DataGenerator,
	private val helper: ExistingFileHelper,
) : BlockStateProvider(generator, RubyMod.MOD_ID, helper) {
	override fun registerStatesAndModels() {
		MODEL_TYPES.forEach { it.generateModels(this) }
	}

	/**
	 * Generates an in-gui version of the block
	 * that appears the same way it does in the world.
	 */
	fun blockItemModel(block: Block) {
		val name = block.registryName!!.path
		blockItemModel(name, name)
	}

	/**
	 * Generates an in-gui version for the
	 * block with the specified parent model.
	 *
	 * @param path the path of the registry name for a model
	 * @param parent the path of the parent model registry name
	 */
	private fun blockItemModel(path: String, parent: String) {
		itemModels().getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(modLoc("block/$parent")))
	}

	private fun stairs(stairs: Block, block: Block) {
		if (stairs is StairsBlock) {
			stairsBlock(stairs, ResourceLocation(RubyMod.MOD_ID, "blocks/" + block.registryName!!.path))
			blockItemModel(stairs)
		} else {
			throw IllegalArgumentException("Not a stair")
		}
	}

	fun simpleItem(item: IItemProvider) {
		val i = item.asItem()
		simpleItem(i.registryName!!.path)
	}

	private fun simpleItem(path: String) =
		itemModels().getBuilder(path)
			.parent(ModelFile.UncheckedModelFile(mcLoc("item/generated")))
			.texture("layer0", modLoc("items/$path"))

	fun blockLoc(b: Block, suffix: String? = null) =
		blockLoc(b.registryName!!.path + (suffix ?: ""), namespace = b.registryName!!.namespace)

	fun blockLoc(path: String, namespace: String = RubyMod.MOD_ID) = ResourceLocation(namespace, "block/$path")

	fun mcFile(path: String) = ModelFile.UncheckedModelFile(mcLoc(path))

	fun modFile(path: String) = ModelFile.UncheckedModelFile(modLoc(path))

	/**
	 * Creates a block model builder for json file [name].
	 *
	 * @param name the name of the .json file to be exported in modid/assets/models/block/
	 */
	fun blockModel(name: String): BlockModelBuilder {
		val outputLoc = extendWithFolder(if (name.contains(":")) mcLoc(name) else modLoc(name))
		val model = models().generatedModels.computeIfAbsent(outputLoc) { k -> BlockModelBuilder(k, helper) }
		BLOCK_MODEL_LOOKUP[model] = outputLoc
		return model
	}

	private fun extendWithFolder(rl: ResourceLocation) =
		if (rl.path.contains('/')) rl else ResourceLocation(rl.namespace, "block/${rl.path}")

	private val BlockModelBuilder.file: ModelFile
		get() {
			return ModelFile.UncheckedModelFile(BLOCK_MODEL_LOOKUP.computeIfAbsent(this) {
				models().generatedModels.entries.first { (_, builder) ->
					builder == this
				}.key
			})
		}

	companion object {
		private val BLOCK_MODEL_LOOKUP = HashMap<BlockModelBuilder, ResourceLocation>()

		val MODEL_TYPES = ArrayList<ModelType<*>>()
	}
}
