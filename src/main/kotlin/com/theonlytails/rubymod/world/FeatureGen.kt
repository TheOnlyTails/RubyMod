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
			event.generation.addFeature(
				GenerationStage.Decoration.UNDERGROUND_DECORATION,
				Feature.NO_SURFACE_ORE.configured(
					OreFeatureConfig(
						OreFeatureConfig.FillerBlockType.NETHERRACK,
						BlockRegistry.rubyOre.defaultBlockState(),
						VEIN_SIZE
					)
				)
					.decorated(Placement.RANGE.configured(TopSolidRangeConfig(MIN_HEIGHT, 0, MAX_HEIGHT)))
					.squared().count(VEINS_PER_CHUNK)
			)
		}
	}
}
