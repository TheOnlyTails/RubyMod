package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.blocks.*;
import net.minecraft.block.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksReg {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", RubyBlock::new);

    public static final RegistryObject<Block> RUBY_SLAB =
            BLOCKS.register("ruby_slab",
                    () -> new SlabBlock(
                            AbstractBlock.Properties.from(RUBY_BLOCK.get())));

    public static final RegistryObject<Block> RUBY_STAIRS =
            BLOCKS.register("ruby_stairs",
                    () -> new StairsBlock(
                            () -> RUBY_BLOCK.get().getDefaultState(),
                            AbstractBlock.Properties.from(RUBY_BLOCK.get())));

    public static final RegistryObject<OreBlock> RUBY_ORE_BLOCK =
            BLOCKS.register("ruby_ore", RubyOreBlock::new);

    public static final RegistryObject<Block> CENTRIFUGE_BLOCK =
            BLOCKS.register("centrifuge", Centrifuge::new);

    public static final RegistryObject<Block> RUBY_WOOL =
            BLOCKS.register("ruby_wool", RubyWool::new);

    public static final RegistryObject<Block> RUBY_CARPET =
            BLOCKS.register("ruby_carpet", RubyCarpet::new);

    public static final RegistryObject<Block> RUBY_BARREL =
            BLOCKS.register("ruby_barrel", RubyBarrelBlock::new);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
