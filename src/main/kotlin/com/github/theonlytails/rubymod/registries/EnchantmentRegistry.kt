@file:Suppress("unused")

package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.enchantments.StingerEnchantment
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object EnchantmentRegistry {
	val ENCHANTMENTS =
		KDeferredRegister(ForgeRegistries.ENCHANTMENTS, RubyMod.MOD_ID)

	val STINGER by ENCHANTMENTS.register("stinger", ::StingerEnchantment)
}