package com.theonlytails.rubymod.world

import com.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraft.world.gen.placement.TopSolidRangeConfig
import net.minecraftforge.event.world.BiomeLoadingEvent

/**
 * Adds features to existing biomes.
 *
 * @author TheOnlyTails
 */
object FeatureGen {
	private const val VEIN_SIZE = 2
	private const val MAX_HEIGHT = 30
	private const val MIN_HEIGHT = 23
	private const val VEINS_PER_CHUNK = 10

	fun addFeaturesToBiomes(event: BiomeLoadingEvent) {
		if (event.category == Biome.Category.NETHER) {
			// func_242731_b -> spreadBase
			event.generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
				Feature.NO_SURFACE_ORE.withConfiguration(
					OreFeatureConfig(
						OreFeatureConfig.FillerBlockType.NETHERRACK,
						BlockRegistry.rubyOre.defaultState,
						VEIN_SIZE))
					.withPlacement(Placement.RANGE.configure(TopSolidRangeConfig(MIN_HEIGHT, 0, MAX_HEIGHT)))
					.square().func_242731_b(VEINS_PER_CHUNK)
			)
		}
	}
}
