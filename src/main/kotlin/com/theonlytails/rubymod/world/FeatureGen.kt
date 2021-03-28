package com.theonlytails.rubymod.world

import com.theonlytails.rubymod.registries.FeatureRegistry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStage
import net.minecraftforge.event.world.BiomeLoadingEvent

/**
 * Adds features to existing biomes.
 *
 * @author TheOnlyTails
 */
fun addFeaturesToBiomes(event: BiomeLoadingEvent) {
	if (event.category == Biome.Category.NETHER)
		event.generation.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, FeatureRegistry.oreRuby)
}