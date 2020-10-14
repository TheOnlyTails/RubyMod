package com.theonlytails.ruby.registries;

import com.theonlytails.ruby.RubyMod;
import com.theonlytails.ruby.enchantments.StingerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentRegistry {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, RubyMod.MOD_ID);

    public static final RegistryObject<Enchantment> STINGER = ENCHANTMENTS.register("stinger", StingerEnchantment::new);
}
