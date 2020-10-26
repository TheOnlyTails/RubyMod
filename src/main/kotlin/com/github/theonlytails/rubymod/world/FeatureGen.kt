package com.github.theonlytails.rubymod.world

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraft.world.gen.placement.TopSolidRangeConfig
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraft.util.ResourceLocation as RL

object FeatureGen {
	private lateinit var ORE_RUBY: ConfiguredFeature<*, *>

	private const val veinSize = 2
	private const val maxHeight = 30
	private const val minHeight = 23
	private const val veinsPerChunk = 10

	fun registerConfiguredFeatures(event: FMLCommonSetupEvent) {
		val registry: Registry<ConfiguredFeature<*, *>> = WorldGenRegistries.CONFIGURED_FEATURE

		// field_241883_b -> NETHERRACK
		// field_242907_l -> RANGE
		// func_242728_a -> square
		// func_242731_b -> spreadBase
		ORE_RUBY = Feature.NO_SURFACE_ORE.withConfiguration(
			OreFeatureConfig(
				OreFeatureConfig.FillerBlockType.field_241883_b,
				BlockRegistry.RUBY_ORE_BLOCK.defaultState,
				veinSize))
			.withPlacement(Placement.field_242907_l.configure(TopSolidRangeConfig(minHeight, 0, maxHeight)))
			.func_242728_a().func_242731_b(veinsPerChunk)

		Registry.register(registry, RL(RubyMod.MOD_ID, "ore_ruby"), ORE_RUBY)
	}

	fun addFeaturesToBiomes(event: BiomeLoadingEvent) {
		if (event.category == Biome.Category.NETHER) {
			event.generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, ORE_RUBY)
		}
	}
}