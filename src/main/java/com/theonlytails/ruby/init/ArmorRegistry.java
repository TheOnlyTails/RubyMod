package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.items.RubyArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ArmorRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<ArmorItem> RUBY_HELMET =
            ItemsRegistry.ITEMS.register("ruby_helmet",
                    () -> new RubyArmorItem(EquipmentSlotType.HEAD));

    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE =
            ItemsRegistry.ITEMS.register("ruby_chestplate",
                    () -> new RubyArmorItem(EquipmentSlotType.CHEST));

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS =
            ItemsRegistry.ITEMS.register("ruby_leggings",
                    () -> new RubyArmorItem(EquipmentSlotType.LEGS));

    public static final RegistryObject<ArmorItem> RUBY_BOOTS =
            ItemsRegistry.ITEMS.register("ruby_boots",
                    () -> new RubyArmorItem(EquipmentSlotType.FEET));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
