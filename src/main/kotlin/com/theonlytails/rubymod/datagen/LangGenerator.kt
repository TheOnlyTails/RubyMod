package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.*
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

/**
 * Generates language files.
 *
 * @author TheOnlyTails
 */
abstract class LangGenerator(generator: DataGenerator, locale: String) :
	LanguageProvider(generator, MOD_ID, locale) {

	class English(generator: DataGenerator) : LangGenerator(generator, "en_us") {
		override fun addTranslations() {
			// Items
			add(ItemRegistry.ruby, "Ruby")
			add(ItemRegistry.poisonedApple, "Poisoned Apple")
			add(ItemRegistry.ghostWaterBucket, "Ghost Water Bucket")
			add(ItemRegistry.rubySheepSpawnEgg, "Ruby Sheep Spawn Egg")

			// Effects and Potions
			add("item.minecraft.potion.effect.motivation", "Potion of Motivation")
			add("item.minecraft.splash_potion.effect.motivation", "Splash Potion of Motivation")
			add("item.minecraft.lingering_potion.effect.motivation", "Lingering Potion of Motivation")
			add("item.minecraft.tipped_arrow.effect.motivation", "Tipped Arrow of Motivation")
			add("item.minecraft.potion.effect.laziness", "Potion of Laziness")
			add("item.minecraft.splash_potion.effect.laziness", "Splash Potion of Laziness")
			add("item.minecraft.lingering_potion.effect.laziness", "Lingering Potion of Laziness")
			add("item.minecraft.tipped_arrow.effect.laziness", "Tipped Arrow of Laziness")

			// Blocks
			add(BlockRegistry.rubyBlock, "Ruby Block")
			add(BlockRegistry.rubySlab, "Ruby Slab")
			add(BlockRegistry.rubyStairs, "Ruby Stairs")
			add(BlockRegistry.rubyPressurePlate, "Ruby Pressure Plate")
			add(BlockRegistry.rubyButton, "Ruby Button")
			add(BlockRegistry.rubyWall, "Ruby Wall")
			add(BlockRegistry.rubyOre, "Ruby Ore")
			add(BlockRegistry.rubyWool, "Ruby Wool")
			add(BlockRegistry.rubyCarpet, "Ruby Carpet")
			add(BlockRegistry.centrifuge, "Centrifuge")
			add(BlockRegistry.rubyBarrel, "Ruby Barrel")
			add(BlockRegistry.logicGate, "Logic Gate")

			// Tools
			add(ItemRegistry.rubySword, "Ruby Sword")
			add(ItemRegistry.rubyPickaxe, "Ruby Pickaxe")
			add(ItemRegistry.rubyAxe, "Ruby Axe")
			add(ItemRegistry.rubyShovel, "Ruby Shovel")
			add(ItemRegistry.rubyHoe, "Ruby Hoe")

			// Armor
			add(ItemRegistry.rubyHelmet, "Ruby Helmet")
			add(ItemRegistry.rubyChestplate, "Ruby Chestplate")
			add(ItemRegistry.rubyLeggings, "Ruby Leggings")
			add(ItemRegistry.rubyBoots, "Ruby Boots")

			// Entities
			add(EntityTypeRegistry.rubySheep, "Ruby Sheep")

			// Enchantments
			add(EnchantmentRegistry.stinger, "Stinger")

			// Biomes
			add("biome.$MOD_ID.ruby_hills", "Ruby Hills")

			// Villager Professions
			add("entity.minecraft.villager.$MOD_ID.jeweler", "Jeweler")

			// Creative tabs
			add("itemGroup.ruby_tab", "RubyMod")
		}
	}
}
