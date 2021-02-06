@file:Suppress("unused")

package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.items.*
import com.theonlytails.rubymod.util.enums.RubyItemTier
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.*
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom items.
 *
 * @author TheOnlyTails
 */
object ItemRegistry {
	val ITEMS = KDeferredRegister(ForgeRegistries.ITEMS, RubyMod.MOD_ID)

	val GHOST_WATER_BUCKET by ITEMS.registerObject("ghost_water_bucket") {
		BucketItem(FluidRegistry::RUBY_GHOST_FLUID,
			Item.Properties()
				.containerItem(Items.BUCKET)
				.maxStackSize(1)
				.group(RubyMod.RUBY_TAB))
	}

	//items
	val POISONED_APPLE by ITEMS.registerObject("poisoned_apple", ::PoisonedAppleItem)

	val RUBY_SHEEP_SPAWN_EGG by ITEMS.registerObject("ruby_sheep_spawn_egg") {
		CustomSpawnEggItem(EntityTypeRegistry::RUBY_SHEEP,
			0xE3E6E7,
			0xFD0D0D,
			RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY by ITEMS.registerObject("ruby", ::RubyItem)

	val RUBY_BLOCK_ITEM by ITEMS.registerObject("ruby_block") {
		BlockItem(BlockRegistry.RUBY_BLOCK, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_SLAB_ITEM by ITEMS.registerObject("ruby_slab") {
		BlockItem(BlockRegistry.RUBY_SLAB, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_STAIRS_ITEM by ITEMS.registerObject("ruby_stairs") {
		BlockItem(BlockRegistry.RUBY_STAIRS, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_PRESSURE_PLATE_ITEM by ITEMS.registerObject("ruby_pressure_plate") {
		BlockItem(BlockRegistry.RUBY_PRESSURE_PLATE, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_BUTTON_ITEM by ITEMS.registerObject("ruby_button") {
		BlockItem(BlockRegistry.RUBY_BUTTON, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_WALL_ITEM by ITEMS.registerObject("ruby_wall") {
		BlockItem(BlockRegistry.RUBY_WALL, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_ORE_BLOCK_ITEM by ITEMS.registerObject("ruby_ore") {
		BlockItem(BlockRegistry.RUBY_ORE_BLOCK, RubyMod.RUBY_TAB_PROPERTY)
	}

	val CENTRIFUGE_BLOCK_ITEM by ITEMS.registerObject("centrifuge") {
		BlockItem(BlockRegistry.CENTRIFUGE_BLOCK, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_WOOL_ITEM by ITEMS.registerObject("ruby_wool") {
		object : BlockItem(BlockRegistry.RUBY_WOOL, RubyMod.RUBY_TAB_PROPERTY) {
			override fun getBurnTime(itemStack: ItemStack) = 100
		}
	}

	val RUBY_CARPET_ITEM by ITEMS.registerObject("ruby_carpet") {
		object : BlockItem(BlockRegistry.RUBY_CARPET, RubyMod.RUBY_TAB_PROPERTY) {
			override fun getBurnTime(itemStack: ItemStack) = 67
		}
	}

	val RUBY_BARREL_ITEM by ITEMS.registerObject("ruby_barrel") {
		BlockItem(BlockRegistry.RUBY_BARREL, RubyMod.RUBY_TAB_PROPERTY)
	}

	val LOGIC_GATE_ITEM by ITEMS.registerObject("logic_gate") {
		BlockItem(BlockRegistry.LOGIC_GATE, RubyMod.RUBY_TAB_PROPERTY)
	}

	//armor
	val RUBY_HELMET by ITEMS.registerObject("ruby_helmet") { RubyArmorItem(EquipmentSlotType.HEAD) }

	val RUBY_CHESTPLATE by ITEMS.registerObject("ruby_chestplate") { RubyArmorItem(EquipmentSlotType.CHEST) }

	val RUBY_LEGGINGS by ITEMS.registerObject("ruby_leggings") { RubyArmorItem(EquipmentSlotType.LEGS) }

	val RUBY_BOOTS by ITEMS.registerObject("ruby_boots") { RubyArmorItem(EquipmentSlotType.FEET) }

	//tools
	val RUBY_PICKAXE by ITEMS.registerObject("ruby_pickaxe") {
		PickaxeItem(RubyItemTier.RUBY, 1, -2.8f, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_SWORD by ITEMS.registerObject("ruby_sword") {
		SwordItem(RubyItemTier.RUBY, 2, -2.4f, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_AXE by ITEMS.registerObject("ruby_axe") {
		AxeItem(RubyItemTier.RUBY, 5f, -3.05f, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_SHOVEL by ITEMS.registerObject("ruby_shovel") {
		ShovelItem(RubyItemTier.RUBY, 1f, -3f, RubyMod.RUBY_TAB_PROPERTY)
	}

	val RUBY_HOE by ITEMS.registerObject("ruby_hoe") {
		HoeItem(RubyItemTier.RUBY, -2, -0.5f, RubyMod.RUBY_TAB_PROPERTY)
	}
}
