package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.data.BlockTagsProvider
import net.minecraft.data.DataGenerator
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ITag
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import java.nio.file.Path

class BlockTagDataGenerator(generator: DataGenerator, helper: ExistingFileHelper) :
	BlockTagsProvider(
		generator,
		RubyMod.MOD_ID,
		helper) {

	/**
	 * A set of all non-modded blocks and tags
	 * that is used to avoid generating unused tags.
	 */
	private var filter: Set<ResourceLocation>? = null

	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		super.registerTags()

		filter = tagToBuilder.entries.map(Map.Entry<ResourceLocation, ITag.Builder>::key).toHashSet()

		// Vanilla tags
		getOrCreateBuilder(BlockTags.BEACON_BASE_BLOCKS).add(BlockRegistry.RUBY_BLOCK)
		getOrCreateBuilder(BlockTags.CARPETS).add(BlockRegistry.RUBY_CARPET)
		getOrCreateBuilder(BlockTags.WOOL).add(BlockRegistry.RUBY_WOOL)

		// Forge Tags
		getOrCreateBuilder(Tags.Blocks.ORES).add(BlockRegistry.RUBY_ORE_BLOCK)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? {
		return if (filter?.contains(id) == true) null else super.makePath(id)
	}
}
