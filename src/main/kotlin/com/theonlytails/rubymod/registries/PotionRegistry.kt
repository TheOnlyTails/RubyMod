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
		Potion(
			"motivation",
			EffectInstance(Effects.MOVEMENT_SPEED, 90 * 20),
			EffectInstance(Effects.JUMP, 90 * 20)
		)
	}

	val longMotivation by potions.registerObject("long_motivation") {
		Potion(
			"motivation",
			EffectInstance(Effects.MOVEMENT_SPEED, 240 * 20),
			EffectInstance(Effects.JUMP, 240 * 20)
		)
	}

	val strongMotivation by potions.registerObject("strong_motivation") {
		Potion(
			"motivation",
			EffectInstance(Effects.MOVEMENT_SPEED, 90 * 20, 1),
			EffectInstance(Effects.JUMP, 90 * 20, 1)
		)
	}

	val laziness by potions.registerObject("laziness") {
		Potion(
			"laziness",
			EffectInstance(Effects.MOVEMENT_SLOWDOWN, 90 * 20),
			EffectInstance(Effects.CONFUSION, 90 * 20)
		)
	}

	val longLaziness by potions.registerObject("long_laziness") {
		Potion(
			"laziness",
			EffectInstance(Effects.MOVEMENT_SLOWDOWN, 240 * 20),
			EffectInstance(Effects.CONFUSION, 240 * 20)
		)
	}

	val strongLaziness by potions.registerObject("strong_laziness") {
		Potion(
			"laziness",
			EffectInstance(Effects.MOVEMENT_SLOWDOWN, 90 * 20, 1),
			EffectInstance(Effects.CONFUSION, 90 * 20, 1)
		)
	}
}
