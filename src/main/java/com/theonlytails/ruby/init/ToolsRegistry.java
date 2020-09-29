package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.items.RubyHoe;
import com.theonlytails.ruby.util.enums.RubyItemTier;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class ToolsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE =
            // A custom pickaxe made out of ruby
            ItemsRegistry.ITEMS.register("ruby_pickaxe", () ->
                    new PickaxeItem(RubyItemTier.RUBY,
                            1,
                            -2.8f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom sword made out of ruby
    public static final RegistryObject<SwordItem> RUBY_SWORD =
            ItemsRegistry.ITEMS.register("ruby_sword", () ->
                    new SwordItem(RubyItemTier.RUBY,
                            2,
                            -2.4f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom axe made out of ruby
    public static final RegistryObject<AxeItem> RUBY_AXE =
            ItemsRegistry.ITEMS.register("ruby_axe", () ->
                    new AxeItem(RubyItemTier.RUBY,
                            5f,
                            -3.05f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom shovel made out of ruby
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL =
            ItemsRegistry.ITEMS.register("ruby_shovel", () ->
                    new ShovelItem(RubyItemTier.RUBY,
                            1f,
                            -3f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom hoe made out of ruby
    public static final RegistryObject<HoeItem> RUBY_HOE =
            ItemsRegistry.ITEMS.register("ruby_hoe", RubyHoe::new);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
