package com.github.theonlytails.rubymod.world

import com.github.theonlytails.rubymod.registries.BiomeRegistry
import com.github.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraft.util.ResourceLocation as RL

object FeatureGen {
	private lateinit var rubyOreFeature: ConfiguredFeature<*, *>

	@Suppress("UNUSED_PARAMETER")
	fun registerFeatures(event: FMLCommonSetupEvent) {
		val registry = WorldGenRegistries.CONFIGURED_FEATURE

		val veinSize = 2
		val maxHeight = 10
		val veinsPerChunk = 10

		rubyOreFeature = Feature.ORE
			.withConfiguration(OreFeatureConfig(
				OreFeatureConfig.FillerBlockType.field_241882_a,
				BlockRegistry.RUBY_ORE_BLOCK.defaultState,
				veinSize
			)).func_242733_d(maxHeight).func_242728_a().func_242731_b(veinsPerChunk)

		Registry.register(registry, RL("ruby_ore_feature"), rubyOreFeature)
	}

	fun onBiomeLoading(event: BiomeLoadingEvent) {
		if (event.name == BiomeRegistry.RUBY_HILLS.registryName) {
			event.generation.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(::rubyOreFeature)
		}
	}
}