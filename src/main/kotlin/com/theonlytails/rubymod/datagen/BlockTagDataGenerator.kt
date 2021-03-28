package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.data.BlockTagsProvider
import net.minecraft.data.DataGenerator
import net.minecraft.tags.BlockTags.*
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.Tags.Blocks
import net.minecraftforge.common.data.ExistingFileHelper
import java.nio.file.Path

/**
 * Generates block tags.
 *
 * @author TheOnlyTails
 */
class BlockTagDataGenerator(generator: DataGenerator, helper: ExistingFileHelper) :
	BlockTagsProvider(generator, MOD_ID, helper) {
	/**
	 * Register tags for each block.
	 */
	override fun addTags() {
		// Vanilla tags
		tag(BEACON_BASE_BLOCKS).add(BlockRegistry.rubyBlock)
		tag(CARPETS).add(BlockRegistry.rubyCarpet)
		tag(WOOL).add(BlockRegistry.rubyWool)
		tag(PRESSURE_PLATES).add(BlockRegistry.rubyPressurePlate)
		tag(BUTTONS).add(BlockRegistry.rubyButton)
		tag(WALLS).add(BlockRegistry.rubyWall)

		// Forge Tags
		tag(Blocks.ORES).add(BlockRegistry.rubyOre)
		tag(Blocks.STORAGE_BLOCKS).add(BlockRegistry.rubyBlock)
	}

	/**
	 * Resolves a Path for the location to save the given tag.
	 */
	override fun getPath(id: ResourceLocation): Path = super.getPath(id)
}
