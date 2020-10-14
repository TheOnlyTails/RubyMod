package com.github.theonlytails.rubymod;

import com.github.theonlytails.rubymod.client.gui.RubyBarrelScreen;
import com.github.theonlytails.rubymod.client.render.RubySheepRenderer;
import com.github.theonlytails.rubymod.entities.RubySheepEntity;
import com.github.theonlytails.rubymod.registries.*;
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
import net.minecraftforge.eventbus.api.IEventBus;
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
@Mod.EventBusSubscriber(modid = RubyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod("rubymod")
public class RubyMod {
    public static final String MOD_ID = "rubymod";

    public static final ItemGroup RUBY_TAB = new ItemGroup("ruby_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.RUBY.get());
        }
    };

    public static final Item.Properties RUBY_TAB_PROPERTY = new Item.Properties().group(RUBY_TAB);

    private static final Logger LOGGER = LogManager.getLogger();

    public RubyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        EntityTypeRegistry.ENTITY_TYPES.register(modEventBus);
        BiomeRegistry.BIOMES.register(modEventBus);
        FluidRegistry.FLUIDS.register(modEventBus);
        TileEntityTypes.TILE_ENTITIES.register(modEventBus);
        ContainerTypeRegistry.CONTAINER_TYPES.register(modEventBus);
        EnchantmentRegistry.ENCHANTMENTS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        PotionRegistry.POTIONS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        BiomeRegistry.registerBiomes();
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(EntityTypeRegistry.RUBY_SHEEP.get(), RubySheepEntity.setCustomAttributes().create());
            PotionBrewing.addMix(
                    Potions.WATER,
                    ItemRegistry.RUBY.get(),
                    PotionRegistry.MOTIVATION.get()
            );

            PotionBrewing.addMix(
                    PotionRegistry.MOTIVATION.get(),
                    Items.GLOWSTONE_DUST,
                    PotionRegistry.STRONG_MOTIVATION.get()
            );

            PotionBrewing.addMix(
                    PotionRegistry.MOTIVATION.get(),
                    Items.REDSTONE,
                    PotionRegistry.LONG_MOTIVATION.get()
            );

            PotionBrewing.addMix(
                    PotionRegistry.MOTIVATION.get(),
                    Items.FERMENTED_SPIDER_EYE,
                    PotionRegistry.LAZINESS.get()
            );

            PotionBrewing.addMix(
                    PotionRegistry.LAZINESS.get(),
                    Items.GLOWSTONE_DUST,
                    PotionRegistry.STRONG_LAZINESS.get()
            );

            PotionBrewing.addMix(
                    PotionRegistry.LAZINESS.get(),
                    Items.REDSTONE,
                    PotionRegistry.LONG_LAZINESS.get()
            );

            ComposterBlock.CHANCES.put(ItemRegistry.POISONED_APPLE.get().asItem(), 0.3f);
        });

        for (RegistryObject<Fluid> fluid : FluidRegistry.FLUIDS.getEntries()) {
            RenderTypeLookup.setRenderLayer(fluid.get(), RenderType.getTranslucent());
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(ContainerTypeRegistry.RUBY_BARREL.get(), RubyBarrelScreen::new));

        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.RUBY_SHEEP.get(), RubySheepRenderer::new);
    }
}
