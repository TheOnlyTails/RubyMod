package com.example.tutorial.util;

import com.example.tutorial.TheOnlyTails;
import com.example.tutorial.blocks.BlockItemBase;
import com.example.tutorial.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    // A registry to hold all of the custom items
    public static final DeferredRegister<Item> ITEMS =
            new DeferredRegister<>(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    // A custom item "Ruby"
    public static final RegistryObject<Item> RUBY =
            ITEMS.register("ruby", ItemBase::new);

    // A block item (inventory form of a block) for the Ruby Block
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM =
            ITEMS.register("ruby_block", () ->
                    new BlockItemBase(RegistryHandlerBlocks.RUBY_BLOCK.get()));

    // A item block for the Ruby Ore
    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM =
            ITEMS.register("ruby_ore", () ->
                    new BlockItemBase(RegistryHandlerBlocks.RUBY_ORE_BLOCK.get()));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}