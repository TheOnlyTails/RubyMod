package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.potion.Potion
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom potions.
 *
 * @author TheOnlyTails
 */
object PotionRegistry {
	val POTIONS = KDeferredRegister(ForgeRegistries.POTION_TYPES, RubyMod.MOD_ID)

	val MOTIVATION by POTIONS.registerObject("motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 90 * 20),
			EffectInstance(Effects.JUMP_BOOST, 90 * 20))
	}

	val LONG_MOTIVATION by POTIONS.registerObject("long_motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 240 * 20),
			EffectInstance(Effects.JUMP_BOOST, 240 * 20))
	}

	val STRONG_MOTIVATION by POTIONS.registerObject("strong_motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 90 * 20, 1),
			EffectInstance(Effects.JUMP_BOOST, 90 * 20, 1)
		)
	}

	val LAZINESS by POTIONS.registerObject("laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 90 * 20),
			EffectInstance(Effects.NAUSEA, 90 * 20)
		)
	}

	val LONG_LAZINESS by POTIONS.registerObject("long_laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 240 * 20),
			EffectInstance(Effects.NAUSEA, 240 * 20)
		)
	}

	val STRONG_LAZINESS by POTIONS.registerObject("strong_laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 90 * 20, 1),
			EffectInstance(Effects.NAUSEA, 90 * 20, 1)
		)
	}
}