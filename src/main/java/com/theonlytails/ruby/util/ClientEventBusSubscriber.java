package com.theonlytails.ruby.util;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.clients.render.RubySheepRenderer;
import com.theonlytails.ruby.init.RubyEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RubyEntityTypes.RUBY_SHEEP.get(), RubySheepRenderer::new);
    }
}
