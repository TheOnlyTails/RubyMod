package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.*
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

abstract class Lang(generator: DataGenerator, locale: String) : LanguageProvider(generator, RubyMod.MOD_ID, locale) {

	class English(generator: DataGenerator) : Lang(generator, "en_us") {
		override fun addTranslations() {
			// Items
			add("item.rubymod.ruby", "Ruby")
			add(ItemRegistry.POISONED_APPLE, "Poisoned Apple")
			add(ItemRegistry.RUBY_WATER_BUCKET, "Ruby Water Bucket")
			add(ItemRegistry.RUBY_SHEEP_SPAWN_EGG, "Ruby Sheep Spawn Egg")

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
			add(BlockRegistry.RUBY_BLOCK, "Ruby Block")
			add(BlockRegistry.RUBY_SLAB, "Ruby Slab")
			add(BlockRegistry.RUBY_STAIRS, "Ruby Stairs")
			add(BlockRegistry.RUBY_PRESSURE_PLATE, "Ruby Pressure Plate")
			add(BlockRegistry.RUBY_BUTTON, "Ruby Button")
			add(BlockRegistry.RUBY_ORE_BLOCK, "Ruby Ore")
			add(BlockRegistry.RUBY_WOOL, "Ruby Wool")
			add(BlockRegistry.RUBY_CARPET, "Ruby Carpet")
			add(BlockRegistry.CENTRIFUGE_BLOCK, "Centrifuge")
			add(BlockRegistry.RUBY_BARREL, "Ruby Barrel")

			// Tools
			add(ItemRegistry.RUBY_SWORD, "Ruby Sword")
			add(ItemRegistry.RUBY_PICKAXE, "Ruby Pickaxe")
			add(ItemRegistry.RUBY_AXE, "Ruby Axe")
			add(ItemRegistry.RUBY_SHOVEL, "Ruby Shovel")
			add(ItemRegistry.RUBY_HOE, "Ruby Hoe")

			// Armor
			add(ItemRegistry.RUBY_HELMET, "Ruby Helmet")
			add(ItemRegistry.RUBY_CHESTPLATE, "Ruby Chestplate")
			add(ItemRegistry.RUBY_LEGGINGS, "Ruby Leggings")
			add(ItemRegistry.RUBY_BOOTS, "Ruby Boots")

			// Entities
			add(EntityTypeRegistry.RUBY_SHEEP, "Ruby Sheep")

			// Enchantments
			add(EnchantmentRegistry.STINGER, "Stinger")

			// Biomes
			add("biome.rubymod.ruby_hills", "Ruby Hills")

			// Creative tabs
			add("itemGroup.ruby_tab", "RubyMod")
		}
	}
}