package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import net.minecraft.block.AbstractBlock
import net.minecraft.block.FlowingFluidBlock
import net.minecraft.block.material.Material
import net.minecraft.item.Rarity
import net.minecraft.util.ResourceLocation
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
	val FLUIDS = KDeferredRegister(ForgeRegistries.FLUIDS, RubyMod.MOD_ID)

	private val STILL_GHOST_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ghost_water_still")
	private val FLOW_GHOST_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ghost_water_flow")
	private val OVERLAY_GHOST_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ghost_water_overlay")

	private val RUBY_GHOST_BLOCK by BlockRegistry.BLOCKS.register("ghost_water_block") {
		FlowingFluidBlock(::RUBY_GHOST_FLUID,
			AbstractBlock.Properties.create(Material.WATER)
				.doesNotBlockMovement()
				.hardnessAndResistance(100f)
				.noDrops())
	}

	val RUBY_GHOST_FLUID by FLUIDS.register("ghost_water") {
		ForgeFlowingFluid.Source(RUBY_WATER_PROP)
	}

	private val RUBY_GHOST_FLOW by FLUIDS.register("ghost_water_flow") {
		ForgeFlowingFluid.Flowing(RUBY_WATER_PROP)
	}

	private val RUBY_WATER_PROP: ForgeFlowingFluid.Properties = ForgeFlowingFluid.Properties(
		{ RUBY_GHOST_FLUID.fluid },
		::RUBY_GHOST_FLOW,
		FluidAttributes.builder(
			STILL_GHOST_WATER,
			FLOW_GHOST_WATER
		).rarity(Rarity.RARE)
			.sound(SoundEvents.BLOCK_WATER_AMBIENT)
			.color(Color(228, 80, 63, 255).rgb)
			.overlay(OVERLAY_GHOST_WATER)
	).block(::RUBY_GHOST_BLOCK).bucket(ItemRegistry::GHOST_WATER_BUCKET)
}