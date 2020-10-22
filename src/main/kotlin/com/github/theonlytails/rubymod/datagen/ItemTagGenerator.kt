package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.data.DataGenerator
import net.minecraft.data.ItemTagsProvider
import net.minecraft.tags.ITag
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import java.nio.file.Path

class ItemTagGenerator(
	generator: DataGenerator,
	blockTags: BlockTagDataGenerator,
	helper: ExistingFileHelper,
) : ItemTagsProvider(
	generator,
	blockTags,
	RubyMod.MOD_ID,
	helper
) {
	private var filter: Set<ResourceLocation>? = null

	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		super.registerTags()

		filter = tagToBuilder.entries.map(Map.Entry<ResourceLocation, ITag.Builder>::key).toHashSet()

		// Vanilla tags
		getOrCreateBuilder(ItemTags.CARPETS).add(ItemRegistry.RUBY_CARPET_ITEM)
		getOrCreateBuilder(ItemTags.WOOL).add(ItemRegistry.RUBY_WOOL_ITEM)
		getOrCreateBuilder(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemRegistry.RUBY)

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
