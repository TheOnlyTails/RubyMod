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
	override fun addTags() {
		// Vanilla tags
		tag(ItemTags.CARPETS).add(ItemRegistry.rubyCarpet)
		tag(ItemTags.WOOL).add(ItemRegistry.rubyWool)
		tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemRegistry.ruby)
		tag(ItemTags.BUTTONS).add(ItemRegistry.rubyButton)
		tag(ItemTags.WALLS).add(ItemRegistry.rubyWall)

		// Forge tags
		tag(Tags.Items.ORES).add(ItemRegistry.rubyOre)
		tag(Tags.Items.GEMS).add(ItemRegistry.ruby)
		tag(Tags.Items.STORAGE_BLOCKS).add(ItemRegistry.rubyBlock)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun getPath(id: ResourceLocation): Path = super.getPath(id)
}
