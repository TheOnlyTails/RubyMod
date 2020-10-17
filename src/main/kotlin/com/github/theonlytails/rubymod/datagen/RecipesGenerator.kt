package com.github.theonlytails.rubymod.datagen

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.advancements.criterion.InventoryChangeTrigger
import net.minecraft.data.*
import net.minecraft.item.Items
import net.minecraft.item.crafting.Ingredient
import net.minecraft.tags.ItemTags
import net.minecraft.util.IItemProvider
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Consumer

class RecipesGenerator(generator: DataGenerator) : RecipeProvider(generator) {
	override fun registerRecipes(consumer: Consumer<IFinishedRecipe>) {
		// Centrifuge
		consumer
			.shaped(ItemRegistry.CENTRIFUGE_BLOCK_ITEM) {
				it.patternLine("iri")
				it.patternLine("ici")
				it.patternLine("idi")
				it.key('i', Items.IRON_INGOT)
				it.key('r', ItemRegistry.RUBY)
				it.key('c', Items.CAULDRON)
				it.key('d', Items.REDSTONE)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Poisoned Apple
		consumer
			.shapeless(ItemRegistry.POISONED_APPLE) {
				it.addIngredient(Items.APPLE)
				it.addIngredient(ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Red dye (from ruby)
		consumer
			.shapeless(Items.RED_DYE, 2) {
				it.addIngredient(ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby (uncrafting from ruby block)
		consumer
			.shapeless(ItemRegistry.RUBY, 9) {
				it.addIngredient(ItemRegistry.RUBY_BLOCK_ITEM)
				it.addCriterion("ruby",
					InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY_BLOCK_ITEM))
			}

		// Ruby Axe
		consumer
			.shaped(ItemRegistry.RUBY_AXE) {
				it.patternLine(" rr")
				it.patternLine(" sr")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.RUBY)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Pickaxe
		consumer
			.shaped(ItemRegistry.RUBY_PICKAXE) {
				it.patternLine("rrr")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.RUBY)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Sword
		consumer
			.shaped(ItemRegistry.RUBY_SWORD) {
				it.patternLine(" r ")
				it.patternLine(" r ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.RUBY)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Shovel
		consumer
			.shaped(ItemRegistry.RUBY_SHOVEL) {
				it.patternLine(" r ")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.RUBY)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Hoe
		consumer
			.shaped(ItemRegistry.RUBY_HOE) {
				it.patternLine(" rr")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.RUBY)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Helmet
		consumer
			.shaped(ItemRegistry.RUBY_HELMET) {
				it.patternLine("rrr")
				it.patternLine("r r")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Chestplate
		consumer
			.shaped(ItemRegistry.RUBY_CHESTPLATE) {
				it.patternLine("r r")
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Leggings
		consumer
			.shaped(ItemRegistry.RUBY_LEGGINGS) {
				it.patternLine("rrr")
				it.patternLine("r r")
				it.patternLine("r r")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Boots
		consumer
			.shaped(ItemRegistry.RUBY_BOOTS) {
				it.patternLine("r r")
				it.patternLine("r r")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Barrel
		consumer
			.shaped(ItemRegistry.RUBY_BARREL_ITEM) {
				it.patternLine("brb")
				it.patternLine("bcb")
				it.patternLine("brb")
				it.key('r', ItemRegistry.RUBY)
				it.key('b', ItemRegistry.RUBY_BLOCK_ITEM)
				it.key('c', Items.BARREL)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Block
		consumer
			.shaped(ItemRegistry.RUBY_BLOCK_ITEM) {
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Slab
		consumer
			.shaped(ItemRegistry.RUBY_SLAB_ITEM, 6) {
				it.patternLine("rrr")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Stairs
		consumer
			.shaped(ItemRegistry.RUBY_STAIRS_ITEM, 4) {
				it.patternLine("r  ")
				it.patternLine("rr ")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.RUBY)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Carpet (from carpet)
		consumer
			.shaped(
				ItemRegistry.RUBY_CARPET_ITEM,
				8,
				ResourceLocation(RubyMod.MOD_ID, "ruby_carpet_from_carpet"),
			) {
				it.patternLine("ccc")
				it.patternLine("crc")
				it.patternLine("ccc")
				it.key('r', ItemRegistry.RUBY)
				it.key('c', Items.WHITE_CARPET)
				it.setGroup("Ruby Carpet")
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Carpet (from wool)
		consumer
			.shaped(
				ItemRegistry.RUBY_CARPET_ITEM,
				8,
				ResourceLocation(RubyMod.MOD_ID, "ruby_carpet_from_wool"),
			) {
				it.patternLine("ww")
				it.key('w', ItemRegistry.RUBY_WOOL_ITEM)
				it.setGroup("Ruby Carpet")
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Smelt ruby ore
		consumer
			.smelting(
				ItemRegistry.RUBY,
				Ingredient.fromItems(ItemRegistry.RUBY_ORE_BLOCK_ITEM),
				1f,
				100,
				ResourceLocation(RubyMod.MOD_ID, "ruby_ore_smelt"),
			) {
				it.addCriterion("ruby_ore", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY_ORE_BLOCK_ITEM))
			}

		// Blast ruby ore
		consumer
			.blasting(
				ItemRegistry.RUBY,
				Ingredient.fromItems(ItemRegistry.RUBY_ORE_BLOCK_ITEM),
				1f,
				100,
				ResourceLocation(RubyMod.MOD_ID, "ruby_ore_blast"),
			) {
				it.addCriterion("ruby_ore", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY_ORE_BLOCK_ITEM))
			}

		// Ruby Water Bucket
		consumer
			.shapeless(ItemRegistry.RUBY_WATER_BUCKET) {
				it.addIngredient(ItemRegistry.RUBY)
				it.addIngredient(Items.WATER_BUCKET)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}

		// Ruby Wool
		consumer
			.shapeless(ItemRegistry.RUBY_WOOL_ITEM) {
				it.addIngredient(ItemRegistry.RUBY)
				it.addIngredient(ItemTags.WOOL)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.RUBY))
			}
	}

	private fun Consumer<IFinishedRecipe>.shaped(
		result: IItemProvider,
		resultCount: Int = 1,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (ShapedRecipeBuilder) -> Unit,
	) {
		val builder = ShapedRecipeBuilder(result, resultCount)
		addIngredients(builder)

		return builder.build(this, id)
	}

	private fun Consumer<IFinishedRecipe>.shapeless(
		result: IItemProvider,
		resultCount: Int = 1,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (ShapelessRecipeBuilder) -> Unit,
	) {
		val builder = ShapelessRecipeBuilder(result, resultCount)
		addIngredients(builder)

		return builder.build(this, id)
	}

	private fun Consumer<IFinishedRecipe>.smelting(
		result: IItemProvider,
		ingredient: Ingredient,
		experience: Float,
		cookingTime: Int,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (CookingRecipeBuilder) -> Unit,
	) {
		val builder = CookingRecipeBuilder.smeltingRecipe(ingredient, result, experience, cookingTime)
		addIngredients(builder)

		return builder.build(this, id)
	}

	private fun Consumer<IFinishedRecipe>.blasting(
		result: IItemProvider,
		ingredient: Ingredient,
		experience: Float,
		cookingTime: Int,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (CookingRecipeBuilder) -> Unit,
	) {
		val builder = CookingRecipeBuilder.blastingRecipe(ingredient, result, experience, cookingTime)
		addIngredients(builder)

		return builder.build(this, id)
	}
}
