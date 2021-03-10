package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.id
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraft.world.gen.placement.TopSolidRangeConfig

object FeatureRegistry {
    val oreRuby = registerFeature(
        "ore_ruby", Feature.NO_SURFACE_ORE.configured(
            OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NETHERRACK,
                BlockRegistry.rubyOre.defaultBlockState(),
                2 // vein size
            )
        ).decorated(
            Placement.RANGE.configured(
                TopSolidRangeConfig(23 /* min height */, 0, 30 /* max height */)
            )
        ).squared().count(10 /* veins per chunk */)
    )
}

private fun <T : IFeatureConfig> registerFeature(
    path: String,
    feature: ConfiguredFeature<T, *>
): ConfiguredFeature<T, *> {
    return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, id(path), feature)
}