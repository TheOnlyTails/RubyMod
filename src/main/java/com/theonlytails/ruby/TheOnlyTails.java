package com.theonlytails.ruby;

import com.theonlytails.ruby.client.gui.RubyBarrelScreen;
import com.theonlytails.ruby.client.render.RubySheepRenderer;
import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.*;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod("ruby")
public class TheOnlyTails {
    public static final String MOD_ID = "ruby";

    public static final ItemGroup RUBY = new ItemGroup("rubyTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsReg.RUBY.get());
        }
    };

    public static final Item.Properties RUBY_TAB_PROP = new Item.Properties().group(RUBY);

    private static final Logger LOGGER = LogManager.getLogger();

    public TheOnlyTails() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        EntityTypes.init();
        BiomesReg.init();
        FluidsReg.init();
        TileEntityTypes.init();
        ContainerTypes.init();
        EnchantReg.init();
        BlocksReg.init();
        ItemsReg.init();
        ToolsReg.init();
        ArmorReg.init();
        PotionsReg.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        BiomesReg.registerBiomes();
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(EntityTypes.RUBY_SHEEP.get(), RubySheepEntity.setCustomAttributes().create());

            PotionBrewing.addMix(
                    Potions.WATER,
                    ItemsReg.RUBY.get(),
                    PotionsReg.LAZINESS.get());

            PotionBrewing.addMix(
                    PotionsReg.LAZINESS.get(),
                    Items.GLOWSTONE_DUST,
                    PotionsReg.STRONG_LAZINESS.get());

            PotionBrewing.addMix(PotionsReg.LAZINESS.get(),
                    Items.REDSTONE,
                    PotionsReg.LONG_LAZINESS.get());
        });

        ComposterBlock.CHANCES.put(ItemsReg.POISONED_APPLE.get().asItem(), 0.3f);

        for (RegistryObject<Fluid> fluid : FluidsReg.FLUIDS.getEntries()) {
            RenderTypeLookup.setRenderLayer(fluid.get(), RenderType.getTranslucent());
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(
                ContainerTypes.RUBY_BARREL.get(), RubyBarrelScreen::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityTypes.RUBY_SHEEP.get(), RubySheepRenderer::new);
    }
}
