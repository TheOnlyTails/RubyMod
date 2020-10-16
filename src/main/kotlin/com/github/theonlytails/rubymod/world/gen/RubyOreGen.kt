package com.github.theonlytails.rubymod.world.gen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.BlockRegistry
import net.minecraft.block.BlockState
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType
import net.minecraft.world.gen.placement.CountRangeConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent
import net.minecraftforge.registries.ForgeRegistries

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object RubyOreGen {
	@SubscribeEvent
	fun genOres(event: FMLLoadCompleteEvent) {
		for (biome in ForgeRegistries.BIOMES) {
			// Nether
			if (biome.category == Biome.Category.NETHER) {
				genOre(
					biome,
					5,
					10,
					5,
					30,
					FillerBlockType.NETHERRACK,
					BlockRegistry.RUBY_ORE_BLOCK.defaultState,
					3
				)
			}
		}
	}

	private fun genOre(
		biome: Biome, count: Int, bottomOffset: Int, topOffset: Int, max: Int,
		fillerBlockType: FillerBlockType,
		defaultBlockState: BlockState, size: Int,
	) {
		val range = CountRangeConfig(count, bottomOffset, topOffset, max)
		val feature = OreFeatureConfig(fillerBlockType, defaultBlockState, size)
		val config = Placement.COUNT_RANGE.configure(range)

		biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
			.withConfiguration(feature)
			.withPlacement(config))
	}
}