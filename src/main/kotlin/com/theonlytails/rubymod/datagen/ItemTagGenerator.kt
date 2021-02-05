package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.data.DataGenerator
import net.minecraft.data.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import java.nio.file.Path

/**
 * Generates item tags.
 *
 * @author TheOnlyTails
 */
class ItemTagGenerator(
	generator: DataGenerator,
	blockTags: BlockTagDataGenerator,
	helper: ExistingFileHelper,
) : ItemTagsProvider(
	generator,
	blockTags,
	com.theonlytails.rubymod.RubyMod.MOD_ID,
	helper
) {

	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		// Vanilla tags
		getOrCreateBuilder(ItemTags.CARPETS).add(ItemRegistry.RUBY_CARPET_ITEM)
		getOrCreateBuilder(ItemTags.WOOL).add(ItemRegistry.RUBY_WOOL_ITEM)
		getOrCreateBuilder(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemRegistry.RUBY)
		getOrCreateBuilder(ItemTags.BUTTONS).add(ItemRegistry.RUBY_BUTTON_ITEM)
		getOrCreateBuilder(ItemTags.WALLS).add(ItemRegistry.RUBY_WALL_ITEM)

		// Forge tags
		getOrCreateBuilder(Tags.Items.ORES).add(ItemRegistry.RUBY_ORE_BLOCK_ITEM)
		getOrCreateBuilder(Tags.Items.GEMS).add(ItemRegistry.RUBY)
		getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(ItemRegistry.RUBY_BLOCK_ITEM)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? = super.makePath(id)
}
