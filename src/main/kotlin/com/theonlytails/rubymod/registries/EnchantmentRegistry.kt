@file:Suppress("unused")

package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.enchantments.StingerEnchantment
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom enchantments.
 *
 * @author TheOnlyTails
 */
object EnchantmentRegistry {
	val enchantments = KDeferredRegister(ForgeRegistries.ENCHANTMENTS, MOD_ID)

	val stinger by enchantments.registerObject("stinger", ::StingerEnchantment)
}
