package com.github.theonlytails.ruby.world.biomes;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class RubyHillsBiome extends Biome {
    public RubyHillsBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .precipitation(RainType.NONE)
                .category(Category.EXTREME_HILLS)
                .depth(0.13f)
                .scale(0.5f)
                .temperature(0.5f)
                .downfall(0.3f)
                .func_235097_a_(new BiomeAmbience.Builder()
                        .setWaterColor(4159204)
                        .setWaterFogColor(329011)
                        .setFogColor(12638463)
                        .setMoodSound(MoodSoundAmbience.field_235027_b_)
                        .build())
                .parent(null)
                .func_235098_a_(ImmutableList.of(
                        new Biome.Attributes(
                                0.0F, 0.0F, 0.0F, 0.0F, 1.0F))));

        this.func_235063_a_(DefaultBiomeFeatures.VILLAGE_PLAINS);
        this.func_235063_a_(DefaultBiomeFeatures.PILLAGER_OUTPOST);
        this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_STANDARD);

        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addHugeMushrooms(this);

        this.addFeature(
                GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.RANDOM_PATCH.withConfiguration(
                        new BlockClusterFeatureConfig.Builder(
                                new WeightedBlockStateProvider().addWeightedBlockstate(Blocks.POPPY.getDefaultState(), 2),
                                SimpleBlockPlacer.field_236447_c_
                        ).tries(64).build()
                ).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(8)))
        );


        this.addSpawn(EntityClassification.CREATURE,
                new SpawnListEntry(
                        EntityType.PIG, 8, 4, 4));

        this.addSpawn(EntityClassification.CREATURE,
                new SpawnListEntry(
                        EntityType.CHICKEN, 8, 4, 4));

        this.addSpawn(EntityClassification.CREATURE,
                new SpawnListEntry(
                        EntityType.MULE, 5, 1, 3));

        this.addSpawn(EntityClassification.AMBIENT,
                new SpawnListEntry(
                        EntityType.BAT, 10, 8, 8));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.SPIDER, 100, 4, 4));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.ZOMBIE, 95, 4, 4));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.ZOMBIE_VILLAGER, 5, 1, 1));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.SKELETON, 100, 4, 4));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.SLIME, 100, 4, 4));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.ENDERMAN, 10, 1, 4));

        this.addSpawn(EntityClassification.MONSTER,
                new SpawnListEntry(
                        EntityType.WITCH, 5, 1, 1));
    }

    @Override
    public int getGrassColor(double posX, double posZ) {
        return 0xe80a0a;
    }
}
