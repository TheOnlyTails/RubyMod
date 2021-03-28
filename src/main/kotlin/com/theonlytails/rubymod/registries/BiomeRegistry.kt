package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.world.makeRubyHills
import net.minecraft.util.RegistryKey
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
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

	val rubyHills by biomes.registerObject("ruby_hills", ::makeRubyHills)
	val rubyHillsRegistryKey: RegistryKey<Biome> = RegistryKey.create(Registry.BIOME_REGISTRY, id("ruby_hills"))
}

fun biomeLoading(event: BiomeLoadingEvent) {
	if (event.name == BiomeRegistry.rubyHills.registryName)
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
			BiomeManager.BiomeEntry(BiomeRegistry.rubyHillsRegistryKey, 6))
}
