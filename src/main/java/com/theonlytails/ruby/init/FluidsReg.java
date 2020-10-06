package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class FluidsReg {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, TheOnlyTails.MOD_ID);


    public static final ResourceLocation STILL_RUBY_WATER =
            new ResourceLocation(TheOnlyTails.MOD_ID, "blocks/ruby_water_still");

    public static final ResourceLocation FLOW_RUBY_WATER =
            new ResourceLocation(TheOnlyTails.MOD_ID, "blocks/ruby_water_flow");

    public static final ResourceLocation OVERLAY_RUBY_WATER =
            new ResourceLocation(TheOnlyTails.MOD_ID, "blocks/ruby_water_overlay");

    public static final RegistryObject<FlowingFluid> RUBY_WATER_FLUID =
            FLUIDS.register("ruby_water", () ->
                    new ForgeFlowingFluid.Source(FluidsReg.RUBY_WATER_PROP));
    public static final RegistryObject<FlowingFluid> RUBY_WATER_FLOW =
            FLUIDS.register("ruby_water_flow", () ->
                    new ForgeFlowingFluid.Flowing(FluidsReg.RUBY_WATER_PROP));
    private static final RegistryObject<FlowingFluidBlock> RUBY_WATER_BLOCK =
            BlocksReg.BLOCKS.register("ruby_water_block", () ->
                    new FlowingFluidBlock(FluidsReg.RUBY_WATER_FLUID,
                            AbstractBlock.Properties.create(Material.WATER)
                                    .doesNotBlockMovement()
                                    .hardnessAndResistance(100f)
                                    .noDrops())
            );
    public static final ForgeFlowingFluid.Properties RUBY_WATER_PROP =
            new ForgeFlowingFluid.Properties(
                    FluidsReg.RUBY_WATER_FLUID,
                    FluidsReg.RUBY_WATER_FLOW,
                    FluidAttributes.builder(
                            STILL_RUBY_WATER,
                            FLOW_RUBY_WATER)
                            .rarity(Rarity.RARE)
                            .sound(SoundEvents.BLOCK_WATER_AMBIENT)
                            .color(new Color(228, 80, 63, 255).getRGB())
                            .overlay(OVERLAY_RUBY_WATER))
                    .block(FluidsReg.RUBY_WATER_BLOCK)
                    .bucket(ItemsReg.RUBY_WATER_BUCKET);


    public static void init() {
        FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
