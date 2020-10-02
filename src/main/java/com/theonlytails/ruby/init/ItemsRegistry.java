package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.blocks.BlockItemBase;
import com.theonlytails.ruby.items.CustomSpawnEgg;
import com.theonlytails.ruby.items.FuelBlockItem;
import com.theonlytails.ruby.items.PoisonedApple;
import com.theonlytails.ruby.items.Ruby;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class ItemsRegistry {
    // A registry to hold all of the custom items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheOnlyTails.MOD_ID);


    public static final RegistryObject<Item> RUBY_WATER_BUCKET =
            ITEMS.register("ruby_water_bucket", () ->
                    new BucketItem(FluidsRegistry.RUBY_WATER_FLUID,
                            new Item.Properties()
                                    .containerItem(BUCKET)
                                    .maxStackSize(1)
                                    .group(TheOnlyTails.RUBY)
                    ));

    // A custom food item named Poisoned Apple
    public static final RegistryObject<PoisonedApple> POISONED_APPLE = ITEMS.register("poisoned_apple", PoisonedApple::new);

    public static final RegistryObject<CustomSpawnEgg> RUBY_SHEEP_SPAWN_EGG = ITEMS.register("ruby_sheep_spawn_egg", () -> new CustomSpawnEgg(EntityTypesRegistry.RUBY_SHEEP, 0xE3E6E7, 0xFD0D0D, new Item.Properties().group(TheOnlyTails.RUBY)));

    // A custom item "Ruby"
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", Ruby::new);

    // A block item (inventory form of a block) for the Ruby Block
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block",
            () -> new BlockItemBase(BlocksRegistry.RUBY_BLOCK.get()));

    // A block item for the Ruby Ore
    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM = ITEMS.register("ruby_ore",
            () -> new BlockItemBase(BlocksRegistry.RUBY_ORE_BLOCK.get()));

    // A block item for the Centrifuge
    public static final RegistryObject<Item> CENTRIFUGE_BLOCK_ITEM = ITEMS.register("centrifuge",
            () -> new BlockItemBase(BlocksRegistry.CENTRIFUGE_BLOCK.get()));

    public static final RegistryObject<Item> RUBY_WOOL_ITEM = ITEMS.register("ruby_wool",
            () -> new FuelBlockItem(BlocksRegistry.RUBY_WOOL.get(), 100));

    public static final RegistryObject<Item> RUBY_CARPET_ITEM = ITEMS.register("ruby_carpet",
            () -> new FuelBlockItem(BlocksRegistry.RUBY_CARPET.get(), 67));

    public static final RegistryObject<Item> RUBY_BARREL_ITEM = ITEMS.register("ruby_barrel",
            () -> new BlockItemBase(BlocksRegistry.RUBY_BARREL.get()));

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}