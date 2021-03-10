package com.theonlytails.rubymod.registries

import com.google.common.collect.ImmutableSet
import com.theonlytails.rubymod.MOD_ID
import net.minecraft.block.Block
import net.minecraft.entity.merchant.villager.VillagerProfession
import net.minecraft.util.SoundEvents
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom villager professions and points of interest.
 *
 * @author TheOnlyTails
 */
object VillagerProfessionsRegistry {
	val professions = KDeferredRegister(ForgeRegistries.PROFESSIONS, MOD_ID)
	val pointsOfInterest = KDeferredRegister(ForgeRegistries.POI_TYPES, MOD_ID)

	val jeweler by professions.registerObject("jeweler") {
		VillagerProfession(
			"jeweler",
			jewelerPOI,
			ImmutableSet.of(),
			ImmutableSet.of(),
			SoundEvents.VILLAGER_WORK_TOOLSMITH
		)
	}

	val jewelerPOI by pointsOfInterest.registerObject("jeweler") {
		PointOfInterestType(
			"jeweler",
			allBlockStates.invoke(BlockRegistry.rubyBarrel),
			1,
			1,
		)
	}

	private val allBlockStates = { it: Block -> ImmutableSet.copyOf(it.stateDefinition.possibleStates) }
}
