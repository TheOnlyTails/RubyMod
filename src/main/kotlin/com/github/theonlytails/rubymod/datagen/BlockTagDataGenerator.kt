package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
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
		RubyMod.MOD_ID,
		helper) {
	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		// Vanilla tags
		getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(BlockRegistry.RUBY_BLOCK)
		getOrCreateBuilder(BlockTags.CARPETS).add(BlockRegistry.RUBY_CARPET)
		getOrCreateBuilder(BlockTags.WOOL).add(BlockRegistry.RUBY_WOOL)
		getOrCreateBuilder(BlockTags.PRESSURE_PLATES).add(BlockRegistry.RUBY_PRESSURE_PLATE)
		getOrCreateBuilder(BlockTags.BUTTONS).add(BlockRegistry.RUBY_BUTTON)
		getOrCreateBuilder(BlockTags.WALLS).add(BlockRegistry.RUBY_WALL)

		// Forge Tags
		getOrCreateBuilder(Tags.Blocks.ORES).add(BlockRegistry.RUBY_ORE_BLOCK)
		getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).add(BlockRegistry.RUBY_BLOCK)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? = super.makePath(id)
}
