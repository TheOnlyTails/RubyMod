package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.enchants.StingerEnchant;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantReg {
    public static final DeferredRegister<Enchantment> ENCHANTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<Enchantment> STINGER =
            ENCHANTS.register("stinger", StingerEnchant::new);

    public static void init() {
        ENCHANTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
