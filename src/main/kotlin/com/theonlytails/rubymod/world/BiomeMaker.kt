package com.theonlytails.rubymod.world

import com.github.theonlytails.rubymod.registries.EntityTypeRegistry
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.math.MathHelper
import net.minecraft.util.registry.WorldGenRegistries
import net.minecraft.world.biome.*
import net.minecraft.world.biome.Biome.*
import net.minecraft.world.gen.feature.structure.StructureFeatures
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder

/**
 * Creates the biomes for registration.
 *
 * @author TheOnlyTails
 */
object BiomeMaker {
	fun makeRubyHills(): Biome {
		val genSettings = genSettings(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)

		WorldGenRegistries.init()

		genSettings.withStructure(StructureFeatures.PILLAGER_OUTPOST)
		genSettings.withStructure(StructureFeatures.VILLAGE_PLAINS)
		genSettings.withStructure(StructureFeatures.RUINED_PORTAL)

		DefaultBiomeFeatures.withCavesAndCanyons(genSettings)
		DefaultBiomeFeatures.withStrongholdAndMineshaft(genSettings)
		DefaultBiomeFeatures.withMonsterRoom(genSettings)
		DefaultBiomeFeatures.withCommonOverworldBlocks(genSettings)
		DefaultBiomeFeatures.withOverworldOres(genSettings)
		DefaultBiomeFeatures.withDisks(genSettings)
		DefaultBiomeFeatures.withMushroomBiomeVegetation(genSettings)
		DefaultBiomeFeatures.withLavaAndWaterSprings(genSettings)
		DefaultBiomeFeatures.withLavaAndWaterLakes(genSettings)

		val spawnSettings = spawnSettings()

		spawnSettings.addSpawn(EntityClassification.CREATURE,
			EntityTypeRegistry.RUBY_SHEEP, 12, 2, 3)

		spawnSettings.addSpawn(EntityClassification.CREATURE,
			EntityType.MULE, 5, 1, 3)

		DefaultBiomeFeatures.withPassiveMobs(spawnSettings)
		DefaultBiomeFeatures.withBatsAndHostiles(spawnSettings)

		return biome(
			precipitation = RainType.NONE,
			category = Category.EXTREME_HILLS,
			depth = 0.13f,
			scale = 0.5f,
			temperature = 0.5f,
			downfall = 0.3f,
			effects(
				grassColor = 0xe80a0a,
				skyColor = getSkyForTemp(0.5f)
			),
			genSettings,
			spawnSettings.build()
		)
	}

	/**
	 * Base biome function
	 * Sky color is not generated
	 */
	private fun biome(
		precipitation: RainType,
		category: Category,
		depth: Float,
		scale: Float,
		temperature: Float,
		downfall: Float,
		effects: BiomeAmbience.Builder,
		genSettings: BiomeGenerationSettings.Builder,
		spawnSettings: MobSpawnInfo = MobSpawnInfo.EMPTY,
	): Biome {
		return Builder()
			.precipitation(precipitation)
			.category(category)
			.depth(depth)
			.scale(scale)
			.temperature(temperature)
			.downfall(downfall)
			.setEffects(effects.build())
			.withGenerationSettings(genSettings.build())
			.withMobSpawnSettings(spawnSettings)
			.build()
	}

	/**
	 * Biome ambience with default parameters and enforced the required ones.
	 * Should prevent slip ups on my part :)
	 */
	private fun effects(
		waterColor: Int = 0x3f76e4,
		waterFogColor: Int = 0x50533,
		grassColor: Int = 0x91bd59,
		foliageColor: Int = 0x77ab2f,
		skyColor: Int,
		skyFogColor: Int = 12638463,
	): BiomeAmbience.Builder {
		return BiomeAmbience.Builder()
			.setWaterColor(waterColor)
			.setWaterFogColor(waterFogColor)
			.withGrassColor(grassColor)
			.withFoliageColor(foliageColor)
			.withSkyColor(skyColor)
			.setFogColor(skyFogColor)
	}

	/** Shortcut function and enforces surface builder */
	private fun <C : ISurfaceBuilderConfig> genSettings(
		surfaceBuilder: SurfaceBuilder<C>,
		config: C,
	): BiomeGenerationSettings.Builder {
		return BiomeGenerationSettings.Builder().withSurfaceBuilder(surfaceBuilder.func_242929_a(config))
	}

	/** Shortcut function */
	private fun spawnSettings(): MobSpawnInfo.Builder {
		return MobSpawnInfo.Builder()
	}

	/** Just for consistency */
	private fun MobSpawnInfo.Builder.build(): MobSpawnInfo {
		return copy()
	}

	/** Shortcut function */
	private fun MobSpawnInfo.Builder.addSpawn(
		classification: EntityClassification,
		entityType: EntityType<*>,
		weight: Int,
		min: Int,
		max: Int,
	): MobSpawnInfo.Builder {
		return withSpawner(classification, MobSpawnInfo.Spawners(entityType, weight, min, max))
	}

	private fun getSkyForTemp(temperature: Float): Int {
		val a = MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f)
		return MathHelper.hsvToRGB(0.62222224f - a * 0.05f, 0.5f + a * 0.1f, 1.0f)
	}
}