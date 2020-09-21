package com.theonlytails.ruby;

import com.theonlytails.ruby.clients.render.RubySheepRenderer;
import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.*;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"deprecation", "RedundantSuppression", "unused"})
@Mod("ruby")
public class TheOnlyTails {
    public static final String MOD_ID = "ruby";

    public static final ItemGroup RUBY = new ItemGroup("rubyTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsRegistry.RUBY.get());
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    public TheOnlyTails() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FluidsRegistry.init();
        RubyEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlocksRegistry.init();
        ItemsRegistry.init();
        ToolsRegistry.init();
        ArmorRegistry.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> GlobalEntityTypeAttributes.put(RubyEntityTypes.RUBY_SHEEP.get(), RubySheepEntity.setCustomAttributes().create()));

        ComposterBlock.CHANCES.put(ItemsRegistry.POISONED_APPLE.get().asItem(), 0.3f);

        for (RegistryObject<Fluid> fluid : FluidsRegistry.FLUIDS.getEntries()) {
            RenderTypeLookup.setRenderLayer(fluid.get(), RenderType.getTranslucent());
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RubyEntityTypes.RUBY_SHEEP.get(), RubySheepRenderer::new);
    }
}
