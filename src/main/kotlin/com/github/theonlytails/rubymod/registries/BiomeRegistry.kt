package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.world.BiomeMaker
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
	val BIOMES = KDeferredRegister(ForgeRegistries.BIOMES, RubyMod.MOD_ID)

	private val RUBY_HILLS by BIOMES.registerObject("ruby_hills", BiomeMaker::makeRubyHills)
	private val rubyHillsRegistryKey = RegistryKey.getOrCreateKey(
		Registry.BIOME_KEY, RubyMod.id("ruby_hills"))

	fun biomeLoading(event: BiomeLoadingEvent) {
		if (event.name == RUBY_HILLS.registryName) {
			BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
				BiomeManager.BiomeEntry(rubyHillsRegistryKey, 6))
		}
	}
}