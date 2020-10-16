package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.world.biomes.RubyHillsBiome
import net.minecraft.world.biome.Biome
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.common.BiomeManager
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object BiomeRegistry {
	val BIOMES = KDeferredRegister(ForgeRegistries.BIOMES, RubyMod.MOD_ID)

	val RUBY_HILLS by BIOMES.register("ruby_hills") { RubyHillsBiome() }

	fun registerBiomes() {
		registerBiome(RUBY_HILLS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD)
	}

	fun registerBiome(biome: Biome?, vararg types: BiomeDictionary.Type?) {
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, BiomeManager.BiomeEntry(biome, 100))
		BiomeDictionary.addTypes(biome, *types)
		BiomeManager.addSpawnBiome(biome)
	}
}