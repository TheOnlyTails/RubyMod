@file:Suppress("unused")

package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.enchantments.StingerEnchantment
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom enchantments.
 *
 * @author TheOnlyTails
 */
object EnchantmentRegistry {
	val ENCHANTMENTS = KDeferredRegister(ForgeRegistries.ENCHANTMENTS, com.theonlytails.rubymod.RubyMod.MOD_ID)

	val STINGER by ENCHANTMENTS.registerObject("stinger", ::StingerEnchantment)
}