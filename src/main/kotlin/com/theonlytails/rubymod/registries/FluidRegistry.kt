package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.id
import net.minecraft.block.AbstractBlock
import net.minecraft.block.FlowingFluidBlock
import net.minecraft.block.material.Material
import net.minecraft.item.Rarity
import net.minecraft.util.SoundEvents
import net.minecraftforge.fluids.FluidAttributes
import net.minecraftforge.fluids.ForgeFlowingFluid
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister
import java.awt.Color

/**
 * Registers custom fluids.
 *
 * @author TheOnlyTails
 */
object FluidRegistry {
	val fluids = KDeferredRegister(ForgeRegistries.FLUIDS, MOD_ID)

	private val stillGhostWaterTexture = id("blocks/ghost_water_still")
	private val flowingGhostWaterTexture = id("blocks/ghost_water_flow")
	private val ghostWaterOverlay = id("blocks/ghost_water_overlay")

	private val ghostWaterBlock by BlockRegistry.blocks.registerObject("ghost_water_block") {
		FlowingFluidBlock(::stillGhostWater,
			AbstractBlock.Properties.create(Material.WATER)
				.doesNotBlockMovement()
				.hardnessAndResistance(100f)
				.noDrops())
	}

	val stillGhostWater by fluids.registerObject("ghost_water") {
		ForgeFlowingFluid.Source(ghostWaterProperties)
	}

	private val flowingGhostWater by fluids.registerObject("ghost_water_flow") {
		ForgeFlowingFluid.Flowing(ghostWaterProperties)
	}

	private val ghostWaterProperties: ForgeFlowingFluid.Properties = ForgeFlowingFluid.Properties(
		{ stillGhostWater.fluid },
		::flowingGhostWater,
		FluidAttributes.builder(
			stillGhostWaterTexture,
			flowingGhostWaterTexture
		).rarity(Rarity.RARE)
			.sound(SoundEvents.BLOCK_WATER_AMBIENT)
			.color(Color(228, 80, 63, 255).rgb)
			.overlay(ghostWaterOverlay)
	).block(::ghostWaterBlock).bucket(ItemRegistry::ghostWaterBucket)
}
