package com.theonlytails.rubymod.registries

import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.potion.Potion
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.versions.forge.ForgeVersion.MOD_ID
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom potions.
 *
 * @author TheOnlyTails
 */
object PotionRegistry {
	val potions = KDeferredRegister(ForgeRegistries.POTION_TYPES, MOD_ID)

	val motivation by potions.registerObject("motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 90 * 20),
			EffectInstance(Effects.JUMP_BOOST, 90 * 20))
	}

	val longMotivation by potions.registerObject("long_motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 240 * 20),
			EffectInstance(Effects.JUMP_BOOST, 240 * 20))
	}

	val strongMotivation by potions.registerObject("strong_motivation") {
		Potion("motivation",
			EffectInstance(Effects.SPEED, 90 * 20, 1),
			EffectInstance(Effects.JUMP_BOOST, 90 * 20, 1)
		)
	}

	val laziness by potions.registerObject("laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 90 * 20),
			EffectInstance(Effects.NAUSEA, 90 * 20)
		)
	}

	val longLaziness by potions.registerObject("long_laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 240 * 20),
			EffectInstance(Effects.NAUSEA, 240 * 20)
		)
	}

	val strongLaziness by potions.registerObject("strong_laziness") {
		Potion("laziness",
			EffectInstance(Effects.SLOWNESS, 90 * 20, 1),
			EffectInstance(Effects.NAUSEA, 90 * 20, 1)
		)
	}
}
