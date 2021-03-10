package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.data.BlockTagsProvider
import net.minecraft.data.DataGenerator
import net.minecraft.tags.BlockTags
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import java.nio.file.Path

/**
 * Generates block tags.
 *
 * @author TheOnlyTails
 */
class BlockTagDataGenerator(generator: DataGenerator, helper: ExistingFileHelper) :
	BlockTagsProvider(
		generator,
		MOD_ID,
		helper) {
	/**
	 * Register tags for each block.
	 */
	override fun addTags() {
		// Vanilla tags
		tag(BlockTags.BEACON_BASE_BLOCKS).add(BlockRegistry.rubyBlock)
		tag(BlockTags.CARPETS).add(BlockRegistry.rubyCarpet)
		tag(BlockTags.WOOL).add(BlockRegistry.rubyWool)
		tag(BlockTags.PRESSURE_PLATES).add(BlockRegistry.rubyPressurePlate)
		tag(BlockTags.BUTTONS).add(BlockRegistry.rubyButton)
		tag(BlockTags.WALLS).add(BlockRegistry.rubyWall)

		// Forge Tags
		tag(Tags.Blocks.ORES).add(BlockRegistry.rubyOre)
		tag(Tags.Blocks.STORAGE_BLOCKS).add(BlockRegistry.rubyBlock)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun getPath(id: ResourceLocation): Path = super.getPath(id)
}
