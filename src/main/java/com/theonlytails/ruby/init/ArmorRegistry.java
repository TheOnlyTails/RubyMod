package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.util.enums.RubyArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArmorRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<ArmorItem> RUBY_HELMET =
            ItemsRegistry.ITEMS.register("ruby_helmet",
                    () -> new ArmorItem(
                            RubyArmorMaterial.RUBY,
                            EquipmentSlotType.HEAD,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE =
            ItemsRegistry.ITEMS.register("ruby_chestplate",
                    () -> new ArmorItem(
                            RubyArmorMaterial.RUBY,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS =
            ItemsRegistry.ITEMS.register("ruby_leggings",
                    () -> new ArmorItem(
                            RubyArmorMaterial.RUBY,
                            EquipmentSlotType.LEGS,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_BOOTS =
            ItemsRegistry.ITEMS.register("ruby_boots",
                    () -> new ArmorItem(
                            RubyArmorMaterial.RUBY,
                            EquipmentSlotType.FEET,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
