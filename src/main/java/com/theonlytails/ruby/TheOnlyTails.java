package com.theonlytails.ruby;

import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.*;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
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
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
