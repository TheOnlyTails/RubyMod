package com.github.theonlytails.rubymod.registries;

import com.github.theonlytails.rubymod.RubyMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class FluidRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, RubyMod.MOD_ID);

    public static final ResourceLocation STILL_RUBY_WATER = new ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_still");

    public static final ResourceLocation FLOW_RUBY_WATER = new ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_flow");

    public static final ResourceLocation OVERLAY_RUBY_WATER = new ResourceLocation(RubyMod.MOD_ID, "blocks/ruby_water_overlay");
    private static final RegistryObject<FlowingFluidBlock> RUBY_WATER_BLOCK = BlockRegistry.BLOCKS.register(
            "ruby_water_block",
            () -> new FlowingFluidBlock(FluidRegistry.RUBY_WATER_FLUID,
                    AbstractBlock.Properties.create(Material.WATER)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(100f)
                            .noDrops())
    );
    public static final ForgeFlowingFluid.Properties RUBY_WATER_PROP = new ForgeFlowingFluid.Properties(
            FluidRegistry.RUBY_WATER_FLUID,
            FluidRegistry.RUBY_WATER_FLOW,
            FluidAttributes.builder(
                    STILL_RUBY_WATER,
                    FLOW_RUBY_WATER
            ).rarity(Rarity.RARE)
                    .sound(SoundEvents.BLOCK_WATER_AMBIENT)
                    .color(new Color(228, 80, 63, 255).getRGB())
                    .overlay(OVERLAY_RUBY_WATER)
    ).block(FluidRegistry.RUBY_WATER_BLOCK).bucket(ItemRegistry.RUBY_WATER_BUCKET);
    public static final RegistryObject<FlowingFluid> RUBY_WATER_FLUID = FLUIDS.register(
            "ruby_water",
            () -> new ForgeFlowingFluid.Source(FluidRegistry.RUBY_WATER_PROP)
    );
    public static final RegistryObject<FlowingFluid> RUBY_WATER_FLOW = FLUIDS.register(
            "ruby_water_flow",
            () -> new ForgeFlowingFluid.Flowing(FluidRegistry.RUBY_WATER_PROP)
    );
}
