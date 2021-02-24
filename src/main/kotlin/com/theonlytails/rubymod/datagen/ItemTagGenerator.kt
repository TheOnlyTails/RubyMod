package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
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
	MOD_ID,
	helper
) {

	/**
	 * Register tags for each block.
	 */
	override fun registerTags() {
		// Vanilla tags
		getOrCreateBuilder(ItemTags.CARPETS).add(ItemRegistry.rubyCarpet)
		getOrCreateBuilder(ItemTags.WOOL).add(ItemRegistry.rubyWool)
		getOrCreateBuilder(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemRegistry.ruby)
		getOrCreateBuilder(ItemTags.BUTTONS).add(ItemRegistry.rubyButton)
		getOrCreateBuilder(ItemTags.WALLS).add(ItemRegistry.rubyWall)

		// Forge tags
		getOrCreateBuilder(Tags.Items.ORES).add(ItemRegistry.rubyOre)
		getOrCreateBuilder(Tags.Items.GEMS).add(ItemRegistry.ruby)
		getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(ItemRegistry.rubyBlock)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun makePath(id: ResourceLocation): Path? = super.makePath(id)
}
