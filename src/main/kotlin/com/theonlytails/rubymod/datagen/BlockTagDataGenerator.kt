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
	override fun registerTags() {
		// Vanilla tags
		getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(BlockRegistry.rubyBlock)
		getOrCreateBuilder(BlockTags.CARPETS).add(BlockRegistry.rubyCarpet)
		getOrCreateBuilder(BlockTags.WOOL).add(BlockRegistry.rubyWool)
		getOrCreateBuilder(BlockTags.PRESSURE_PLATES).add(BlockRegistry.rubyPressurePlate)
		getOrCreateBuilder(BlockTags.BUTTONS).add(BlockRegistry.rubyButton)
		getOrCreateBuilder(BlockTags.WALLS).add(BlockRegistry.rubyWall)

		// Forge Tags
		getOrCreateBuilder(Tags.Blocks.ORES).add(BlockRegistry.rubyOre)
		getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).add(BlockRegistry.rubyBlock)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? = super.makePath(id)
}
