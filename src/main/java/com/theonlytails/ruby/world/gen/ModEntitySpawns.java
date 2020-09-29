package com.theonlytails.ruby.world.gen;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.init.EntityTypesRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnRubySheep(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            Biome.Category category = biome.getCategory();

            if (category != Biome.Category.NETHER &&
                    category != Biome.Category.THEEND &&
                    category != Biome.Category.OCEAN) {

                if (biome != Biomes.WOODED_BADLANDS_PLATEAU && biome != Biomes.SNOWY_TUNDRA) {
                    biome.getSpawns(EntityClassification.CREATURE)
                            .add(new Biome.SpawnListEntry(EntityTypesRegistry.RUBY_SHEEP.get(),
                                    12, 2, 3));
                }
            }
        }
    }
}
