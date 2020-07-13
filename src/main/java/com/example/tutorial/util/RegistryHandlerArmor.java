package com.example.tutorial.util;

import com.example.tutorial.TheOnlyTails;
import com.example.tutorial.armor.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandlerArmor {
    public static final DeferredRegister<Item> ITEMS =
            new DeferredRegister<>(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<ArmorItem> RUBY_HELMET =
            RegistryHandler.ITEMS.register("ruby_helmet",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.HEAD,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE =
            RegistryHandler.ITEMS.register("ruby_chestplate",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.CHEST,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS =
            RegistryHandler.ITEMS.register("ruby_leggings",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.LEGS,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static final RegistryObject<ArmorItem> RUBY_BOOTS =
            RegistryHandler.ITEMS.register("ruby_boots",
                    () -> new ArmorItem(
                            ModArmorMaterial.RUBY,
                            EquipmentSlotType.FEET,
                            new Item.Properties().group(TheOnlyTails.RUBY)));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
