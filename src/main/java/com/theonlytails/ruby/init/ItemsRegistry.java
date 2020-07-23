package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.blocks.BlockItemBase;
import com.theonlytails.ruby.items.ItemBase;
import com.theonlytails.ruby.items.PoisonedApple;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemsRegistry {
    // A registry to hold all of the custom items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    // A custom item "Ruby"
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

    // A block item (inventory form of a block) for the Ruby Block
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () ->
            new BlockItemBase(BlocksRegistry.RUBY_BLOCK.get()));

    // A block item  for the Ruby Ore
    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM = ITEMS.register("ruby_ore", () ->
            new BlockItemBase(BlocksRegistry.RUBY_ORE_BLOCK.get()));

    // A block item for the Centrifuge
    public static final RegistryObject<Item> CENTRIFUGE_BLOCK_ITEM = ITEMS.register("centrifuge", () ->
            new BlockItemBase(BlocksRegistry.CENTRIFUGE_BLOCK.get()));

    // A custom food item named Poisoned Apple
    public static final RegistryObject<PoisonedApple> POISONED_APPLE = ITEMS.register("poisoned_apple", PoisonedApple::new);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}