package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.items.CustomSpawnEgg;
import com.theonlytails.ruby.items.FuelBlockItem;
import com.theonlytails.ruby.items.PoisonedApple;
import com.theonlytails.ruby.items.Ruby;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class ItemsReg {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<Item> RUBY_WATER_BUCKET =
            ITEMS.register("ruby_water_bucket", () ->
                    new BucketItem(FluidsReg.RUBY_WATER_FLUID,
                            new Item.Properties()
                                    .containerItem(BUCKET)
                                    .maxStackSize(1)
                                    .group(TheOnlyTails.RUBY)
                    ));

    public static final RegistryObject<PoisonedApple> POISONED_APPLE = ITEMS.register("poisoned_apple", PoisonedApple::new);

    public static final RegistryObject<CustomSpawnEgg> RUBY_SHEEP_SPAWN_EGG = ITEMS.register("ruby_sheep_spawn_egg", () -> new CustomSpawnEgg(EntityTypes.RUBY_SHEEP, 0xE3E6E7, 0xFD0D0D, TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", Ruby::new);

    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block",
            () -> new BlockItem(BlocksReg.RUBY_BLOCK.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> RUBY_SLAB_ITEM = ITEMS.register("ruby_slab",
            () -> new BlockItem(BlocksReg.RUBY_SLAB.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> RUBY_STAIRS_ITEM = ITEMS.register("ruby_stairs",
            () -> new BlockItem(BlocksReg.RUBY_STAIRS.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM = ITEMS.register("ruby_ore",
            () -> new BlockItem(BlocksReg.RUBY_ORE_BLOCK.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> CENTRIFUGE_BLOCK_ITEM = ITEMS.register("centrifuge",
            () -> new BlockItem(BlocksReg.CENTRIFUGE_BLOCK.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static final RegistryObject<Item> RUBY_WOOL_ITEM = ITEMS.register("ruby_wool",
            () -> new FuelBlockItem(BlocksReg.RUBY_WOOL.get(), 100));

    public static final RegistryObject<Item> RUBY_CARPET_ITEM = ITEMS.register("ruby_carpet",
            () -> new FuelBlockItem(BlocksReg.RUBY_CARPET.get(), 67));

    public static final RegistryObject<Item> RUBY_BARREL_ITEM = ITEMS.register("ruby_barrel",
            () -> new BlockItem(BlocksReg.RUBY_BARREL.get(), TheOnlyTails.RUBY_TAB_PROP));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}