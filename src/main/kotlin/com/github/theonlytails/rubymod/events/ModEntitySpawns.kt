package com.github.theonlytails.rubymod.events

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.EntityTypeRegistry
import net.minecraft.entity.EntityClassification
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biome.SpawnListEntry
import net.minecraft.world.biome.Biomes
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent
import net.minecraftforge.registries.ForgeRegistries

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object ModEntitySpawns {

	@SubscribeEvent
	fun spawnRubySheep(event: FMLLoadCompleteEvent) {
		for (biome in ForgeRegistries.BIOMES) {
			val category = biome.category

			if (category != Biome.Category.NETHER &&
				category != Biome.Category.THEEND &&
				category != Biome.Category.OCEAN
			) {
				if (biome !== Biomes.WOODED_BADLANDS_PLATEAU && biome !== Biomes.SNOWY_TUNDRA) {
					biome.getSpawns(EntityClassification.CREATURE).add(SpawnListEntry(
						EntityTypeRegistry.RUBY_SHEEP,
						12,
						2,
						3
					))
				}
			}
		}
	}
}