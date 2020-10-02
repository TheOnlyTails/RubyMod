package com.theonlytails.ruby.world.gen;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.init.BiomesRegistry;
import com.theonlytails.ruby.init.BlocksRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RubyOreGen {

    @SubscribeEvent
    public static void genOres(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            // Nether
            if (biome.getCategory() == Biome.Category.NETHER) {
                genOre(biome,
                        5, 10, 5,
                        30, OreFeatureConfig.FillerBlockType.NETHERRACK,
                        BlocksRegistry.RUBY_ORE_BLOCK.get().getDefaultState(), 3);
            }

            if (biome == BiomesRegistry.RUBY_HILLS.get()) {
                genOre(biome,
                        5, 10, 5,
                        30, OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        BlocksRegistry.RUBY_ORE_BLOCK.get().getDefaultState(), 3);
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max,
                               OreFeatureConfig.FillerBlockType fillerBlockType,
                               BlockState defaultBlockState, int size) {
        CountRangeConfig range = new CountRangeConfig(count, bottomOffset,
                topOffset, max);

        OreFeatureConfig feature = new OreFeatureConfig(fillerBlockType, defaultBlockState, size);

        ConfiguredPlacement<CountRangeConfig> config = Placement.COUNT_RANGE.configure(range);

        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(feature)
                        .withPlacement(config));
    }
}
