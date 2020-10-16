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

object FluidRegistry {
	val FLUIDS = KDeferredRegister(ForgeRegistries.FLUIDS, RubyMod.MOD_ID)

	private val STILL_RUBY_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_still")
	private val FLOW_RUBY_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_flow")
	private val OVERLAY_RUBY_WATER = ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_overlay")

	private val RUBY_WATER_BLOCK by BlockRegistry.BLOCKS.register("ruby_water_block") {
		FlowingFluidBlock({ RUBY_WATER_FLUID },
			AbstractBlock.Properties.create(Material.WATER)
				.doesNotBlockMovement()
				.hardnessAndResistance(100f)
				.noDrops())
	}

	val RUBY_WATER_FLUID by FLUIDS.register("ruby_water") {
		ForgeFlowingFluid.Source(RUBY_WATER_PROP)
	}

	private val RUBY_WATER_FLOW by FLUIDS.register("ruby_water_flow") {
		ForgeFlowingFluid.Flowing(RUBY_WATER_PROP)
	}

	private val RUBY_WATER_PROP: ForgeFlowingFluid.Properties = ForgeFlowingFluid.Properties(
		{ RUBY_WATER_FLUID.fluid },
		{ RUBY_WATER_FLOW },
		FluidAttributes.builder(
			STILL_RUBY_WATER,
			FLOW_RUBY_WATER
		).rarity(Rarity.RARE)
			.sound(SoundEvents.BLOCK_WATER_AMBIENT)
			.color(Color(228, 80, 63, 255).rgb)
			.overlay(OVERLAY_RUBY_WATER)
	).block { RUBY_WATER_BLOCK }.bucket { ItemRegistry.RUBY_WATER_BUCKET }
}