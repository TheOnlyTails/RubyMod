package com.github.theonlytails.rubymod.registries;

import com.github.theonlytails.rubymod.RubyMod;
import com.github.theonlytails.rubymod.items.CustomSpawnEggItem;
import com.github.theonlytails.rubymod.items.FuelBlockItem;
import com.github.theonlytails.rubymod.items.RubyArmorItem;
import com.github.theonlytails.rubymod.items.RubyItem;
import com.github.theonlytails.rubymod.util.enums.RubyItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.item.Items.BUCKET;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RubyMod.MOD_ID);

    public static final RegistryObject<Item> RUBY_WATER_BUCKET = ITEMS.register(
            "ruby_water_bucket",
            () -> new BucketItem(
                    FluidRegistry.RUBY_WATER_FLUID,
                    new Item.Properties()
                            .containerItem(BUCKET)
                            .maxStackSize(1)
                            .group(RubyMod.RUBY_TAB)
            ));

    //items
    public static final RegistryObject<Item> POISONED_APPLE = ITEMS.register(
            "poisoned_apple",
            () -> new Item(new Item.Properties()
                    .group(ItemGroup.FOOD)
                    .food(new Food.Builder()
                            .hunger(7)
                            .saturation(1.2f)
                            // Gives you Nausea 2 for 7 seconds 100% of the time;
                            .effect(() -> new EffectInstance(Effects.NAUSEA, 7 * 20, 1), 1f)
                            // Gives you Poison 2 for 9 seconds 100% of the time;
                            .effect(() -> new EffectInstance(Effects.POISON, 9 * 20, 1), 1f)
                            // Gives you Glowing 1 for 10 seconds 100% of the time;
                            .effect(() -> new EffectInstance(Effects.GLOWING, 10 * 20, 0), 1f)
                            // Gives you Hunger 3 for 3 seconds 10% of the time;
                            .effect(() -> new EffectInstance(Effects.HUNGER, 3 * 20, 2), 0.1f)
                            // Gives you Blindness (!) 3 for 5 seconds 5% of the time;
                            .effect(() -> new EffectInstance(Effects.BLINDNESS, 5 * 20, 2), 0.05f)
                            // Gives you Luck (!) 1 for 1 seconds 50% of the time;
                            .effect(() -> new EffectInstance(Effects.LUCK, 20, 0), 0.5f)
                            // You can eat it even if you're not hungry;
                            .setAlwaysEdible()
                            .build())
            ));

    public static final RegistryObject<CustomSpawnEggItem> RUBY_SHEEP_SPAWN_EGG = ITEMS.register("ruby_sheep_spawn_egg", () -> new CustomSpawnEggItem(EntityTypeRegistry.RUBY_SHEEP, 0xE3E6E7, 0xFD0D0D, RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", RubyItem::new);

    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block",
            () -> new BlockItem(BlockRegistry.RUBY_BLOCK.get(), RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> RUBY_SLAB_ITEM = ITEMS.register("ruby_slab",
            () -> new BlockItem(BlockRegistry.RUBY_SLAB.get(), RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> RUBY_STAIRS_ITEM = ITEMS.register("ruby_stairs",
            () -> new BlockItem(BlockRegistry.RUBY_STAIRS.get(), RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> RUBY_ORE_BLOCK_ITEM = ITEMS.register("ruby_ore",
            () -> new BlockItem(BlockRegistry.RUBY_ORE_BLOCK.get(), RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> CENTRIFUGE_BLOCK_ITEM = ITEMS.register("centrifuge",
            () -> new BlockItem(BlockRegistry.CENTRIFUGE_BLOCK.get(), RubyMod.RUBY_TAB_PROPERTY));

    public static final RegistryObject<Item> RUBY_WOOL_ITEM = ITEMS.register("ruby_wool",
            () -> new FuelBlockItem(BlockRegistry.RUBY_WOOL.get(), 100));

    public static final RegistryObject<Item> RUBY_CARPET_ITEM = ITEMS.register("ruby_carpet",
            () -> new FuelBlockItem(BlockRegistry.RUBY_CARPET.get(), 67));

    public static final RegistryObject<Item> RUBY_BARREL_ITEM = ITEMS.register("ruby_barrel",
            () -> new BlockItem(BlockRegistry.RUBY_BARREL.get(), RubyMod.RUBY_TAB_PROPERTY));

    //armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ItemRegistry.ITEMS.register(
            "ruby_helmet",
            () -> new RubyArmorItem(EquipmentSlotType.HEAD)
    );

    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ItemRegistry.ITEMS.register(
            "ruby_chestplate",
            () -> new RubyArmorItem(EquipmentSlotType.CHEST)
    );

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ItemRegistry.ITEMS.register(
            "ruby_leggings",
            () -> new RubyArmorItem(EquipmentSlotType.LEGS)
    );

    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ItemRegistry.ITEMS.register(
            "ruby_boots",
            () -> new RubyArmorItem(EquipmentSlotType.FEET)
    );

    //tools
    //A custom pickaxe made out of ruby
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ItemRegistry.ITEMS.register(
            "ruby_pickaxe",
            () -> new PickaxeItem(
                    RubyItemTier.RUBY,
                    1,
                    -2.8f,
                    RubyMod.RUBY_TAB_PROPERTY)
    );

    // A custom sword made out of ruby
    public static final RegistryObject<SwordItem> RUBY_SWORD = ItemRegistry.ITEMS.register(
            "ruby_sword",
            () -> new SwordItem(
                    RubyItemTier.RUBY,
                    2,
                    -2.4f,
                    RubyMod.RUBY_TAB_PROPERTY)
    );

    // A custom axe made out of ruby
    public static final RegistryObject<AxeItem> RUBY_AXE = ItemRegistry.ITEMS.register(
            "ruby_axe",
            () -> new AxeItem(RubyItemTier.RUBY,
                    5f,
                    -3.05f,
                    RubyMod.RUBY_TAB_PROPERTY)
    );

    // A custom shovel made out of ruby
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ItemRegistry.ITEMS.register(
            "ruby_shovel",
            () -> new ShovelItem(RubyItemTier.RUBY,
                    1f,
                    -3f,
                    RubyMod.RUBY_TAB_PROPERTY)
    );

    // A custom hoe made out of ruby
    public static final RegistryObject<HoeItem> RUBY_HOE = ItemRegistry.ITEMS.register(
            "ruby_hoe",
            () -> new HoeItem(RubyItemTier.RUBY,
                    -2,
                    -0.5f,
                    RubyMod.RUBY_TAB_PROPERTY)
    );
}