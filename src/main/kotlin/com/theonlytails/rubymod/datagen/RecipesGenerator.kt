package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.advancements.criterion.InventoryChangeTrigger
import net.minecraft.data.*
import net.minecraft.item.Items
import net.minecraft.item.crafting.Ingredient
import net.minecraft.tags.ItemTags
import net.minecraft.util.IItemProvider
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.ForgeRegistries
import java.util.function.Consumer

/**
 * Generates recipes of all types.
 *
 * @author TheOnlyTails
 */
class RecipesGenerator(generator: DataGenerator) : RecipeProvider(generator) {
	override fun registerRecipes(consumer: Consumer<IFinishedRecipe>) {
		// Centrifuge
		consumer
			.shaped(ItemRegistry.centrifuge) {
				it.patternLine("iri")
				it.patternLine("ici")
				it.patternLine("idi")
				it.key('i', Items.IRON_INGOT)
				it.key('r', ItemRegistry.ruby)
				it.key('c', Items.CAULDRON)
				it.key('d', Items.REDSTONE)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Poisoned Apple
		consumer
			.shapeless(ItemRegistry.poisonedApple) {
				it.addIngredient(Items.APPLE)
				it.addIngredient(ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby (uncrafting from ruby block)
		consumer
			.shapeless(ItemRegistry.ruby, 9) {
				it.addIngredient(ItemRegistry.rubyBlock)
				it.addCriterion("ruby",
					InventoryChangeTrigger.Instance.forItems(ItemRegistry.rubyBlock))
			}

		// Ruby Axe
		consumer
			.shaped(ItemRegistry.rubyAxe) {
				it.patternLine(" rr")
				it.patternLine(" sr")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.ruby)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Pickaxe
		consumer
			.shaped(ItemRegistry.rubyPickaxe) {
				it.patternLine("rrr")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.ruby)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Sword
		consumer
			.shaped(ItemRegistry.rubySword) {
				it.patternLine(" r ")
				it.patternLine(" r ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.ruby)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Shovel
		consumer
			.shaped(ItemRegistry.rubyShovel) {
				it.patternLine(" r ")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.ruby)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Hoe
		consumer
			.shaped(ItemRegistry.rubyHoe) {
				it.patternLine(" rr")
				it.patternLine(" s ")
				it.patternLine(" s ")
				it.key('r', ItemRegistry.ruby)
				it.key('s', Items.STICK)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Helmet
		consumer
			.shaped(ItemRegistry.rubyHelmet) {
				it.patternLine("rrr")
				it.patternLine("r r")
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Chestplate
		consumer
			.shaped(ItemRegistry.rubyChestplate) {
				it.patternLine("r r")
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Leggings
		consumer
			.shaped(ItemRegistry.rubyLeggings) {
				it.patternLine("rrr")
				it.patternLine("r r")
				it.patternLine("r r")
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Boots
		consumer
			.shaped(ItemRegistry.rubyBoots) {
				it.patternLine("r r")
				it.patternLine("r r")
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Barrel
		consumer
			.shaped(ItemRegistry.rubyBarrel) {
				it.patternLine("brb")
				it.patternLine("bcb")
				it.patternLine("brb")
				it.key('r', ItemRegistry.ruby)
				it.key('b', ItemRegistry.rubyBlock)
				it.key('c', Items.BARREL)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Block
		consumer
			.shaped(ItemRegistry.rubyBlock) {
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Slab
		consumer
			.shaped(ItemRegistry.rubySlab, 6) {
				it.patternLine("rrr")
				it.key('r', ItemRegistry.rubyBlock)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Stairs
		consumer
			.shaped(ItemRegistry.rubyStairs, 4) {
				it.patternLine("r  ")
				it.patternLine("rr ")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.rubyBlock)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Button
		consumer
			.shapeless(ItemRegistry.rubyButton) {
				it.addIngredient(ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Pressure Plate
		consumer
			.shaped(ItemRegistry.rubyPressurePlate) {
				it.patternLine("rr")
				it.key('r', ItemRegistry.rubyBlock)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Wall
		consumer
			.shaped(ItemRegistry.rubyWall, 6) {
				it.patternLine("rrr")
				it.patternLine("rrr")
				it.key('r', ItemRegistry.rubyBlock)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Carpet (from carpet)
		consumer
			.shaped(
				ItemRegistry.rubyCarpet,
				8,
				ResourceLocation(MOD_ID, "ruby_carpet_from_carpet"),
			) {
				it.patternLine("ccc")
				it.patternLine("crc")
				it.patternLine("ccc")
				it.key('r', ItemRegistry.ruby)
				it.key('c', Items.WHITE_CARPET)
				it.setGroup("Ruby Carpet")
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Carpet (from wool)
		consumer
			.shaped(
				ItemRegistry.rubyCarpet,
				8,
				ResourceLocation(MOD_ID, "ruby_carpet_from_wool"),
			) {
				it.patternLine("ww")
				it.key('w', ItemRegistry.rubyWool)
				it.setGroup("Ruby Carpet")
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Smelt ruby ore
		consumer
			.smelting(
				ItemRegistry.ruby,
				Ingredient.fromItems(ItemRegistry.rubyOre),
				1f,
				100,
				ResourceLocation(MOD_ID, "ruby_ore_smelt"),
			) {
				it.addCriterion("ruby_ore", InventoryChangeTrigger.Instance.forItems(ItemRegistry.rubyOre))
			}

		// Blast ruby ore
		consumer
			.blasting(
				ItemRegistry.ruby,
				Ingredient.fromItems(ItemRegistry.rubyOre),
				1f,
				100,
				ResourceLocation(MOD_ID, "ruby_ore_blast"),
			) {
				it.addCriterion("ruby_ore", InventoryChangeTrigger.Instance.forItems(ItemRegistry.rubyOre))
			}

		// Ruby Water Bucket
		consumer
			.shapeless(ItemRegistry.ghostWaterBucket) {
				it.addIngredient(ItemRegistry.ruby)
				it.addIngredient(Items.WATER_BUCKET)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Ruby Wool
		consumer
			.shapeless(ItemRegistry.rubyWool) {
				it.addIngredient(ItemRegistry.ruby)
				it.addIngredient(ItemTags.WOOL)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
			}

		// Logic Gate
		consumer
			.shaped(ItemRegistry.logicGate) {
				it.patternLine("t t")
				it.patternLine("srs")
				it.key('t', Items.REDSTONE_TORCH)
				it.key('s', ItemTags.STONE_CRAFTING_MATERIALS)
				it.key('r', ItemRegistry.ruby)
				it.addCriterion("ruby", InventoryChangeTrigger.Instance.forItems(ItemRegistry.ruby))
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
