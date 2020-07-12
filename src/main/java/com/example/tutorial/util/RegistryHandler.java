package com.example.tutorial.util;

import com.example.tutorial.TheOnlyTails;
import com.example.tutorial.armor.ModArmorMaterial;
import com.example.tutorial.blocks.BlockItemBase;
import com.example.tutorial.blocks.RubyBlock;
import com.example.tutorial.blocks.RubyOreBlock;
import com.example.tutorial.items.ItemBase;
import com.example.tutorial.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    // A registry to hold all of the custom items
    public static final DeferredRegister<Item> ITEMS =
            new DeferredRegister<>(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);
    // A registry to hold all of the custom blocks
    public static final DeferredRegister<Block> BLOCKS =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, TheOnlyTails.MOD_ID);

    // A custom item "Ruby"
    public static final RegistryObject<Item> RUBY =
            ITEMS.register("ruby", ItemBase::new);

    // A custom block "Ruby Block"
    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", RubyBlock::new);

    // A block item (inventory form of a block) for the Ruby Block
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM =
            ITEMS.register("ruby_block", () -> new BlockItemBase(RUBY_BLOCK.get()));

    // A custom block "Ruby Ore"
    public static final RegistryObject<Block> RUBY_ORE_BLOCK =
            BLOCKS.register("ruby_ore", RubyOreBlock::new);

    // A item block for the Ruby Ore
    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM =
            ITEMS.register("ruby_ore", () -> new BlockItemBase(RUBY_ORE_BLOCK.get()));

    // A custom sword made out of ruby
    public static final RegistryObject<SwordItem> RUBY_SWORD =
            ITEMS.register("ruby_sword", () ->
                    new SwordItem(ModItemTier.RUBY,
                            1,
                            -2.4f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom pickaxe made out of ruby
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE =
            ITEMS.register("ruby_pickaxe", () ->
                    new PickaxeItem(ModItemTier.RUBY,
                            0,
                            -2.8f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom axe made out of ruby
    public static final RegistryObject<AxeItem> RUBY_AXE =
            ITEMS.register("ruby_axe", () ->
                    new AxeItem(ModItemTier.RUBY,
                            5f,
                            -3.1f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom shovel made out of ruby
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL =
            ITEMS.register("ruby_shovel", () ->
                    new ShovelItem(ModItemTier.RUBY,
                            0.5f,
                            -3f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom hoe made out of ruby
    public static final RegistryObject<HoeItem> RUBY_HOE =
            ITEMS.register("ruby_hoe", () ->
                    new HoeItem(ModItemTier.RUBY,
                            -1f,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_HELMET =
            ITEMS.register("ruby_helmet",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.HEAD,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE =
            ITEMS.register("ruby_chestplate",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS =
            ITEMS.register("ruby_leggings",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.LEGS,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_BOOTS =
            ITEMS.register("ruby_boots",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.FEET,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}