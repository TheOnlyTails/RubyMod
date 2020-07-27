package com.theonlytails.ruby;

import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.*;
import com.theonlytails.ruby.items.RubySheepSpawnEgg;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Mod("ruby")
public class TheOnlyTails {
    public static final String MOD_ID = "ruby";

    public static final ItemGroup RUBY = new ItemGroup("rubyTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsRegistry.RUBY.get());
        }
    };

    @SuppressWarnings("unused")
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
        DeferredWorkQueue.runLater(() -> DispenserBlock.registerDispenseBehavior(ItemsRegistry.RUBY_SHEEP_SPAWN_EGG::get,
                new DefaultDispenseItemBehavior() {
                    public @NotNull ItemStack dispenseStack(@NotNull IBlockSource source, @NotNull ItemStack stack) {
                        Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                        ((RubySheepSpawnEgg) stack.getItem()).getType(stack.getTag()).spawn(source.getWorld(),
                                stack,
                                null,
                                source.getBlockPos().offset(direction),
                                SpawnReason.DISPENSER,
                                direction != Direction.UP,
                                false);
                        stack.shrink(1);
                        return stack;
                    }
                }
        ));
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
