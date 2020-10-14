package com.github.theonlytails.ruby.registries;

import com.github.theonlytails.ruby.RubyMod;
import com.github.theonlytails.ruby.blocks.CentrifugeBlock;
import com.github.theonlytails.ruby.blocks.RubyBarrelBlock;
import com.github.theonlytails.ruby.blocks.RubyCarpetBlock;
import com.github.theonlytails.ruby.blocks.RubyOreBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RubyMod.MOD_ID);

    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register(
            "ruby_block",
            () -> new Block(Block.Properties.create(Material.IRON)
                    .hardnessAndResistance(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(2)
                    .setRequiresTool())
    );

    public static final RegistryObject<Block> RUBY_SLAB = BLOCKS.register(
            "ruby_slab",
            () -> new SlabBlock(AbstractBlock.Properties.from(RUBY_BLOCK.get()))
    );

    public static final RegistryObject<Block> RUBY_STAIRS = BLOCKS.register(
            "ruby_stairs",
            () -> new StairsBlock(() -> RUBY_BLOCK.get().getDefaultState(), AbstractBlock.Properties.from(RUBY_BLOCK.get()))
    );

    public static final RegistryObject<OreBlock> RUBY_ORE_BLOCK = BLOCKS.register("ruby_ore", RubyOreBlock::new);

    public static final RegistryObject<Block> CENTRIFUGE_BLOCK = BLOCKS.register("centrifuge", CentrifugeBlock::new);

    public static final RegistryObject<Block> RUBY_WOOL = BLOCKS.register(
            "ruby_wool",
            () -> new Block(Block.Properties
                    .create(Material.WOOL, MaterialColor.CRIMSON_HYPHAE)
                    .hardnessAndResistance(0.8f)
                    .sound(SoundType.CLOTH))
    );

    public static final RegistryObject<Block> RUBY_CARPET = BLOCKS.register("ruby_carpet", RubyCarpetBlock::new);

    public static final RegistryObject<Block> RUBY_BARREL = BLOCKS.register("ruby_barrel", RubyBarrelBlock::new);
}
