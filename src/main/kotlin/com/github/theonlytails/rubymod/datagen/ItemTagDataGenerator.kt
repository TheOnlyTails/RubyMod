package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.data.DataGenerator
import net.minecraft.data.ItemTagsProvider
import net.minecraft.tags.ITag
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags
import java.nio.file.Path

class ItemTagDataGenerator(
	generator: DataGenerator,
	blockTags: BlockTagDataGenerator,
) : ItemTagsProvider(
	generator,
	blockTags,
) {
	var filter: Set<ResourceLocation>? = null

	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		super.registerTags()

		filter = tagToBuilder.entries.map(Map.Entry<ResourceLocation, ITag.Builder>::key).toHashSet()

		// Vanilla tags
		getOrCreateBuilder(ItemTags.CARPETS).add(ItemRegistry.RUBY_CARPET_ITEM)
		getOrCreateBuilder(ItemTags.WOOL).add(ItemRegistry.RUBY_WOOL_ITEM)
		// field_232908_Z_ -> BEACON_PAYMENT_ITEMS
		getOrCreateBuilder(ItemTags.field_232908_Z_).add(ItemRegistry.RUBY)

		// Forge tags
		getOrCreateBuilder(Tags.Items.ORES).add(ItemRegistry.RUBY_ORE_BLOCK_ITEM)
		getOrCreateBuilder(Tags.Items.GEMS).add(ItemRegistry.RUBY)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? {
		return if (filter?.contains(id) == true) null else super.makePath(id)
	}
}
