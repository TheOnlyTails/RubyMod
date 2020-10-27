package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.google.common.collect.ImmutableSet
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.merchant.villager.VillagerProfession
import net.minecraft.util.SoundEvents
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object VillagerProfessionsRegistry {
	val PROFESSIONS = KDeferredRegister(ForgeRegistries.PROFESSIONS, RubyMod.MOD_ID)
	val POINTS_OF_INTEREST = KDeferredRegister(ForgeRegistries.POI_TYPES, RubyMod.MOD_ID)

	val JEWELER by PROFESSIONS.register("jeweler") {
		VillagerProfession(
			"jeweler",
			JEWELER_POI,
			ImmutableSet.of(),
			ImmutableSet.of(),
			SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH
		)
	}

	val JEWELER_POI by POINTS_OF_INTEREST.register("jeweler") {
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