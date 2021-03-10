package com.theonlytails.rubymod.world

import com.theonlytails.rubymod.registries.EntityTypeRegistry
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.math.MathHelper
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
@Suppress("SameParameterValue")
object BiomeMaker {
	fun makeRubyHills(): Biome {
		val genSettings = genSettings(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS)

		genSettings.addStructureStart(StructureFeatures.PILLAGER_OUTPOST)
		genSettings.addStructureStart(StructureFeatures.VILLAGE_PLAINS)
		genSettings.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD)

		DefaultBiomeFeatures.addDefaultCarvers(genSettings)
		DefaultBiomeFeatures.addDefaultOverworldLandStructures(genSettings)
		DefaultBiomeFeatures.addDefaultMonsterRoom(genSettings)
		DefaultBiomeFeatures.addDefaultUndergroundVariety(genSettings)
		DefaultBiomeFeatures.addDefaultOres(genSettings)
		DefaultBiomeFeatures.addDefaultSoftDisks(genSettings)
		DefaultBiomeFeatures.addMushroomFieldVegetation(genSettings)
		DefaultBiomeFeatures.addDefaultSprings(genSettings)
		DefaultBiomeFeatures.addDefaultLakes(genSettings)

		val spawnSettings = spawnSettings()
			.addSpawn(EntityClassification.CREATURE, EntityTypeRegistry.rubySheep, 12, 2, 3)
			.addSpawn(EntityClassification.CREATURE, EntityType.MULE, 5, 1, 3)

		DefaultBiomeFeatures.farmAnimals(spawnSettings)
		DefaultBiomeFeatures.commonSpawns(spawnSettings)

		return biome(
			precipitation = RainType.NONE,
			category = Category.EXTREME_HILLS,
			depth = 0.13f,
			scale = 0.5f,
			temperature = 0.5f,
			downfall = 0.3f,
			effects(grassColor = 0xe80a0a, skyColor = getSkyForTemp(0.5f)),
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
	) = Builder()
		.precipitation(precipitation)
		.biomeCategory(category)
		.depth(depth)
		.scale(scale)
		.temperature(temperature)
		.downfall(downfall)
		.specialEffects(effects.build())
		.generationSettings(genSettings.build())
		.mobSpawnSettings(spawnSettings)
		.build()

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
	) = BiomeAmbience.Builder()
		.waterColor(waterColor)
		.waterFogColor(waterFogColor)
		.grassColorOverride(grassColor)
		.foliageColorOverride(foliageColor)
		.skyColor(skyColor)
		.fogColor(skyFogColor)

	/** Shortcut function and enforces surface builder */
	private fun <C : ISurfaceBuilderConfig> genSettings(
		surfaceBuilder: SurfaceBuilder<C>,
		config: C,
	) = BiomeGenerationSettings.Builder().surfaceBuilder(surfaceBuilder.configured(config))

	/** Shortcut function */
	private fun spawnSettings() = MobSpawnInfo.Builder()

	/** Shortcut function */
	private fun MobSpawnInfo.Builder.addSpawn(
		classification: EntityClassification,
		entityType: EntityType<*>,
		weight: Int,
		min: Int,
		max: Int,
	) = addSpawn(classification, MobSpawnInfo.Spawners(entityType, weight, min, max))

	private fun getSkyForTemp(temperature: Float): Int {
		val a = MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f)
		return MathHelper.hsvToRgb(0.62222224f - a * 0.05f, 0.5f + a * 0.1f, 1.0f)
	}
}
