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
public class ToolsReg {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE =
            // A custom pickaxe made out of ruby
            ItemsReg.ITEMS.register("ruby_pickaxe", () ->
                    new PickaxeItem(RubyItemTier.RUBY,
                            1,
                            -2.8f,
                            TheOnlyTails.RUBY_TAB_PROP));

    // A custom sword made out of ruby
    public static final RegistryObject<SwordItem> RUBY_SWORD =
            ItemsReg.ITEMS.register("ruby_sword", () ->
                    new SwordItem(RubyItemTier.RUBY,
                            2,
                            -2.4f,
                            TheOnlyTails.RUBY_TAB_PROP));

    // A custom axe made out of ruby
    public static final RegistryObject<AxeItem> RUBY_AXE =
            ItemsReg.ITEMS.register("ruby_axe", () ->
                    new AxeItem(RubyItemTier.RUBY,
                            5f,
                            -3.05f,
                            TheOnlyTails.RUBY_TAB_PROP));

    // A custom shovel made out of ruby
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL =
            ItemsReg.ITEMS.register("ruby_shovel", () ->
                    new ShovelItem(RubyItemTier.RUBY,
                            1f,
                            -3f,
                            TheOnlyTails.RUBY_TAB_PROP));

    // A custom hoe made out of ruby
    public static final RegistryObject<HoeItem> RUBY_HOE =
            ItemsReg.ITEMS.register("ruby_hoe", RubyHoe::new);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
