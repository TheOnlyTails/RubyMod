package com.github.theonlytails.rubymod.world.biomes

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.*
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.placement.ChanceConfig
import net.minecraft.world.gen.placement.Placement
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder

class RubyHillsBiome : Biome(Builder()
	.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
	.precipitation(RainType.NONE)
	.category(Category.EXTREME_HILLS)
	.depth(0.13f)
	.scale(0.5f)
	.temperature(0.5f)
	.downfall(0.3f)
	.func_235097_a_(BiomeAmbience.Builder()
		.setWaterColor(4159204)
		.setWaterFogColor(329011)
		.setFogColor(12638463)
		.setMoodSound(MoodSoundAmbience.field_235027_b_)
		.build())
	.parent(null)
	.func_235098_a_(ImmutableList.of(
		Attributes(
			0.0f, 0.0f, 0.0f, 0.0f, 1.0f)))) {

	override fun getGrassColor(posX: Double, posZ: Double): Int {
		return 0xe80a0a
	}

	init {
		func_235063_a_(DefaultBiomeFeatures.VILLAGE_PLAINS)
		func_235063_a_(DefaultBiomeFeatures.PILLAGER_OUTPOST)
		func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_STANDARD)

		DefaultBiomeFeatures.addCarvers(this)
		DefaultBiomeFeatures.addLakes(this)
		DefaultBiomeFeatures.addMonsterRooms(this)
		DefaultBiomeFeatures.addStoneVariants(this)
		DefaultBiomeFeatures.addOres(this)
		DefaultBiomeFeatures.addSedimentDisks(this)
		DefaultBiomeFeatures.addSprings(this)
		DefaultBiomeFeatures.addHugeMushrooms(this)

		addFeature(
			GenerationStage.Decoration.VEGETAL_DECORATION,
			Feature.RANDOM_PATCH.withConfiguration(
				BlockClusterFeatureConfig.Builder(
					WeightedBlockStateProvider().addWeightedBlockstate(Blocks.POPPY.defaultState, 2),
					SimpleBlockPlacer.field_236447_c_
				).tries(64).build()
			).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(ChanceConfig(8)))
		)

		addSpawn(EntityClassification.CREATURE,
			SpawnListEntry(
				EntityType.PIG, 8, 4, 4))
		addSpawn(EntityClassification.CREATURE,
			SpawnListEntry(
				EntityType.CHICKEN, 8, 4, 4))

		addSpawn(EntityClassification.CREATURE,
			SpawnListEntry(
				EntityType.MULE, 5, 1, 3))

		addSpawn(EntityClassification.AMBIENT,
			SpawnListEntry(
				EntityType.BAT, 10, 8, 8))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.SPIDER, 100, 4, 4))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.ZOMBIE, 95, 4, 4))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.ZOMBIE_VILLAGER, 5, 1, 1))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.SKELETON, 100, 4, 4))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.SLIME, 100, 4, 4))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.ENDERMAN, 10, 1, 4))

		addSpawn(EntityClassification.MONSTER,
			SpawnListEntry(
				EntityType.WITCH, 5, 1, 1))
	}
}