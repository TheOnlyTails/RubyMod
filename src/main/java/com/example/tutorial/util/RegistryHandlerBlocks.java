package com.example.tutorial.util;

import com.example.tutorial.TheOnlyTails;
import com.example.tutorial.blocks.RubyBlock;
import com.example.tutorial.blocks.RubyOreBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandlerBlocks {
    // A registry to hold all of the custom blocks
    public static final DeferredRegister<Block> BLOCKS =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, TheOnlyTails.MOD_ID);

    // A custom block "Ruby Block"
    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", RubyBlock::new);

    // A custom block "Ruby Ore"
    public static final RegistryObject<Block> RUBY_ORE_BLOCK =
            BLOCKS.register("ruby_ore", RubyOreBlock::new);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
