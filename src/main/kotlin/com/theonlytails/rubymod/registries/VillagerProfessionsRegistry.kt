package com.theonlytails.rubymod.registries

import com.google.common.collect.ImmutableSet
import net.minecraft.block.Block
import net.minecraft.block.BlockState
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
	val PROFESSIONS = KDeferredRegister(ForgeRegistries.PROFESSIONS, com.theonlytails.rubymod.RubyMod.MOD_ID)
	val POINTS_OF_INTEREST = KDeferredRegister(ForgeRegistries.POI_TYPES, com.theonlytails.rubymod.RubyMod.MOD_ID)

	val JEWELER by PROFESSIONS.registerObject("jeweler") {
		VillagerProfession(
			"jeweler",
			JEWELER_POI,
			ImmutableSet.of(),
			ImmutableSet.of(),
			SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH
		)
	}

	val JEWELER_POI by POINTS_OF_INTEREST.registerObject("jeweler") {
		PointOfInterestType(
			"jeweler",
			getAllStates(BlockRegistry.RUBY_BARREL),
			1,
			1,
		)
	}

	private fun getAllStates(blockIn: Block): Set<BlockState> {
		return ImmutableSet.copyOf(blockIn.stateContainer.validStates)
	}
}
