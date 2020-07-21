package com.theonlytails.ruby.util;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.tools.RubyItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandlerTools {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    // A custom sword made out of ruby
    public static final RegistryObject<SwordItem> RUBY_SWORD =
            RegistryHandler.ITEMS.register("ruby_sword", () ->
                    new SwordItem(RubyItemTier.RUBY,
                            2,
                            -2.4f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom pickaxe made out of ruby
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE =
            RegistryHandler.ITEMS.register("ruby_pickaxe", () ->
                    new PickaxeItem(RubyItemTier.RUBY,
                            0,
                            -2.8f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom axe made out of ruby
    public static final RegistryObject<AxeItem> RUBY_AXE =
            RegistryHandler.ITEMS.register("ruby_axe", () ->
                    new AxeItem(RubyItemTier.RUBY,
                            5f,
                            -3.05f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom shovel made out of ruby
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL =
            RegistryHandler.ITEMS.register("ruby_shovel", () ->
                    new ShovelItem(RubyItemTier.RUBY,
                            1f,
                            -3f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom hoe made out of ruby
    public static final RegistryObject<HoeItem> RUBY_HOE =
            RegistryHandler.ITEMS.register("ruby_hoe", () ->
                    new HoeItem(RubyItemTier.RUBY,
                            1,
                            -3f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
