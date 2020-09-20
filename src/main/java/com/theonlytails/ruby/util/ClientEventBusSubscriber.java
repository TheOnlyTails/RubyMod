package com.theonlytails.ruby.util;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.clients.render.RubySheepRenderer;
import com.theonlytails.ruby.init.FluidsRegistry;
import com.theonlytails.ruby.init.RubyEntityTypes;
import com.theonlytails.ruby.items.CustomSpawnEgg;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RubyEntityTypes.RUBY_SHEEP.get(), RubySheepRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        CustomSpawnEgg.initSpawnEggs();
    }

    @SubscribeEvent
    public static void noFogInRubyWater(EntityViewRenderEvent.FogColors event) {
        ActiveRenderInfo info = event.getInfo();
        FluidState fluidState = info.getFluidState();
        FluidState rubyWaterFluidState = FluidsRegistry.RUBY_WATER_FLUID.get().getDefaultState();

        if (fluidState.equals(rubyWaterFluidState)
                && info.getRenderViewEntity() instanceof ClientPlayerEntity) {

            event.setRed(255);
        }
    }
}
