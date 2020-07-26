package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.blocks.Centrifuge;
import com.theonlytails.ruby.blocks.RubyBlock;
import com.theonlytails.ruby.blocks.RubyOreBlock;
import com.theonlytails.ruby.blocks.RubyWool;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.Environment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksRegistry {
    // A registry to hold all of the custom blocks
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheOnlyTails.MOD_ID);

    // A custom block "Ruby Block"
    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", RubyBlock::new);

    // A custom block "Ruby Ore"
    public static final RegistryObject<OreBlock> RUBY_ORE_BLOCK =
            BLOCKS.register("ruby_ore", RubyOreBlock::new);

    // A custom block "Ruby Ore"
    public static final RegistryObject<Block> CENTRIFUGE_BLOCK =
            BLOCKS.register("centrifuge", Centrifuge::new);

    public static final RegistryObject<Block> RUBY_WOOL =
            BLOCKS.register("ruby_wool", RubyWool::new);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
