package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.world.BiomeMaker
import net.minecraft.util.RegistryKey
import net.minecraft.util.registry.Registry
import net.minecraftforge.common.BiomeManager
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers and adds custom biomes to the world.
 *
 * @author TheOnlyTails
 */
object BiomeRegistry {
	val biomes = KDeferredRegister(ForgeRegistries.BIOMES, MOD_ID)

	private val rubyHills by biomes.registerObject("ruby_hills", BiomeMaker::makeRubyHills)
	private val rubyHillsRegistryKey = RegistryKey.getOrCreateKey(
		Registry.BIOME_KEY, id("ruby_hills"))

	fun biomeLoading(event: BiomeLoadingEvent) {
		if (event.name == rubyHills.registryName) {
			BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
				BiomeManager.BiomeEntry(rubyHillsRegistryKey, 6))
		}
	}
}
