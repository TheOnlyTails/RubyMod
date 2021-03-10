@file:Suppress("unused")

package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.items.CustomSpawnEggItem
import com.theonlytails.rubymod.items.PoisonedApple
import com.theonlytails.rubymod.items.Ruby
import com.theonlytails.rubymod.items.RubyArmor
import com.theonlytails.rubymod.rubyTab
import com.theonlytails.rubymod.rubyTabProperty
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
	val items = KDeferredRegister(ForgeRegistries.ITEMS, MOD_ID)

	val ghostWaterBucket by items.registerObject("ghost_water_bucket") {
		BucketItem(
			FluidRegistry::stillGhostWater,
			Item.Properties()
				.craftRemainder(Items.BUCKET)
				.stacksTo(1)
				.tab(rubyTab)
		)
	}

	//items
	val poisonedApple by items.registerObject("poisoned_apple", ::PoisonedApple)

	val rubySheepSpawnEgg by items.registerObject("ruby_sheep_spawn_egg") {
		CustomSpawnEggItem(EntityTypeRegistry::rubySheep,
			0xE3E6E7,
			0xFD0D0D)
	}

	val ruby by items.registerObject("ruby", ::Ruby)

	val rubyBlock by items.registerObject("ruby_block") {
		BlockItem(BlockRegistry.rubyBlock, rubyTabProperty)
	}

	val rubySlab by items.registerObject("ruby_slab") {
		BlockItem(BlockRegistry.rubySlab, rubyTabProperty)
	}

	val rubyStairs by items.registerObject("ruby_stairs") {
		BlockItem(BlockRegistry.rubyStairs, rubyTabProperty)
	}

	val rubyPressurePlate by items.registerObject("ruby_pressure_plate") {
		BlockItem(BlockRegistry.rubyPressurePlate, rubyTabProperty)
	}

	val rubyButton by items.registerObject("ruby_button") {
		BlockItem(BlockRegistry.rubyButton, rubyTabProperty)
	}

	val rubyWall by items.registerObject("ruby_wall") {
		BlockItem(BlockRegistry.rubyWall, rubyTabProperty)
	}

	val rubyOre by items.registerObject("ruby_ore") {
		BlockItem(BlockRegistry.rubyOre, rubyTabProperty)
	}

	val centrifuge by items.registerObject("centrifuge") {
		BlockItem(BlockRegistry.centrifuge, rubyTabProperty)
	}

	val rubyWool by items.registerObject("ruby_wool") {
		object : BlockItem(BlockRegistry.rubyWool, rubyTabProperty) {
			override fun getBurnTime(itemStack: ItemStack) = 100
		}
	}

	val rubyCarpet by items.registerObject("ruby_carpet") {
		object : BlockItem(BlockRegistry.rubyCarpet, rubyTabProperty) {
			override fun getBurnTime(itemStack: ItemStack) = 67
		}
	}

	val rubyBarrel by items.registerObject("ruby_barrel") {
		BlockItem(BlockRegistry.rubyBarrel, rubyTabProperty)
	}

	val logicGate by items.registerObject("logic_gate") {
		BlockItem(BlockRegistry.logicGate, rubyTabProperty)
	}

	//armor
	val rubyHelmet by items.registerObject("ruby_helmet") { RubyArmor(EquipmentSlotType.HEAD) }

	val rubyChestplate by items.registerObject("ruby_chestplate") { RubyArmor(EquipmentSlotType.CHEST) }

	val rubyLeggings by items.registerObject("ruby_leggings") { RubyArmor(EquipmentSlotType.LEGS) }

	val rubyBoots by items.registerObject("ruby_boots") { RubyArmor(EquipmentSlotType.FEET) }

	//tools
	val rubyPickaxe by items.registerObject("ruby_pickaxe") {
		PickaxeItem(RubyItemTier.RUBY, 1, -2.8f, rubyTabProperty)
	}

	val rubySword by items.registerObject("ruby_sword") {
		SwordItem(RubyItemTier.RUBY, 2, -2.4f, rubyTabProperty)
	}

	val rubyAxe by items.registerObject("ruby_axe") {
		AxeItem(RubyItemTier.RUBY, 5f, -3.05f, rubyTabProperty)
	}

	val rubyShovel by items.registerObject("ruby_shovel") {
		ShovelItem(RubyItemTier.RUBY, 1f, -3f, rubyTabProperty)
	}

	val rubyHoe by items.registerObject("ruby_hoe") {
		HoeItem(RubyItemTier.RUBY, -2, -0.5f, rubyTabProperty)
	}
}
