package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.advancements.criterion.InventoryChangeTrigger.Instance.hasItems
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
	override fun buildShapelessRecipes(consumer: Consumer<IFinishedRecipe>) {
		// Centrifuge
		consumer
			.shaped(ItemRegistry.centrifuge) {
				it.pattern("iri")
				it.pattern("ici")
				it.pattern("idi")
				it.define('i', Items.IRON_INGOT)
				it.define('r', ItemRegistry.ruby)
				it.define('c', Items.CAULDRON)
				it.define('d', Items.REDSTONE)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Poisoned Apple
		consumer
			.shapeless(ItemRegistry.poisonedApple) {
				it.requires(Items.APPLE)
				it.requires(ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby (uncrafting from ruby block)
		consumer
			.shapeless(ItemRegistry.ruby, 9) {
				it.requires(ItemRegistry.rubyBlock)
				it.unlockedBy("ruby", hasItems(ItemRegistry.rubyBlock))
			}

		// Ruby Axe
		consumer
			.shaped(ItemRegistry.rubyAxe) {
				it.pattern(" rr")
				it.pattern(" sr")
				it.pattern(" s ")
				it.define('r', ItemRegistry.ruby)
				it.define('s', Items.STICK)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Pickaxe
		consumer
			.shaped(ItemRegistry.rubyPickaxe) {
				it.pattern("rrr")
				it.pattern(" s ")
				it.pattern(" s ")
				it.define('r', ItemRegistry.ruby)
				it.define('s', Items.STICK)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Sword
		consumer
			.shaped(ItemRegistry.rubySword) {
				it.pattern(" r ")
				it.pattern(" r ")
				it.pattern(" s ")
				it.define('r', ItemRegistry.ruby)
				it.define('s', Items.STICK)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Shovel
		consumer
			.shaped(ItemRegistry.rubyShovel) {
				it.pattern(" r ")
				it.pattern(" s ")
				it.pattern(" s ")
				it.define('r', ItemRegistry.ruby)
				it.define('s', Items.STICK)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Hoe
		consumer
			.shaped(ItemRegistry.rubyHoe) {
				it.pattern(" rr")
				it.pattern(" s ")
				it.pattern(" s ")
				it.define('r', ItemRegistry.ruby)
				it.define('s', Items.STICK)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Helmet
		consumer
			.shaped(ItemRegistry.rubyHelmet) {
				it.pattern("rrr")
				it.pattern("r r")
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Chestplate
		consumer
			.shaped(ItemRegistry.rubyChestplate) {
				it.pattern("r r")
				it.pattern("rrr")
				it.pattern("rrr")
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Leggings
		consumer
			.shaped(ItemRegistry.rubyLeggings) {
				it.pattern("rrr")
				it.pattern("r r")
				it.pattern("r r")
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Boots
		consumer
			.shaped(ItemRegistry.rubyBoots) {
				it.pattern("r r")
				it.pattern("r r")
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Barrel
		consumer
			.shaped(ItemRegistry.rubyBarrel) {
				it.pattern("brb")
				it.pattern("bcb")
				it.pattern("brb")
				it.define('r', ItemRegistry.ruby)
				it.define('b', ItemRegistry.rubyBlock)
				it.define('c', Items.BARREL)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Block
		consumer
			.shaped(ItemRegistry.rubyBlock) {
				it.pattern("rrr")
				it.pattern("rrr")
				it.pattern("rrr")
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Slab
		consumer
			.shaped(ItemRegistry.rubySlab, 6) {
				it.pattern("rrr")
				it.define('r', ItemRegistry.rubyBlock)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Stairs
		consumer
			.shaped(ItemRegistry.rubyStairs, 4) {
				it.pattern("r  ")
				it.pattern("rr ")
				it.pattern("rrr")
				it.define('r', ItemRegistry.rubyBlock)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Button
		consumer
			.shapeless(ItemRegistry.rubyButton) {
				it.requires(ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Pressure Plate
		consumer
			.shaped(ItemRegistry.rubyPressurePlate) {
				it.pattern("rr")
				it.define('r', ItemRegistry.rubyBlock)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Wall
		consumer
			.shaped(ItemRegistry.rubyWall, 6) {
				it.pattern("rrr")
				it.pattern("rrr")
				it.define('r', ItemRegistry.rubyBlock)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Carpet (from carpet)
		consumer
			.shaped(
				ItemRegistry.rubyCarpet,
				8,
				ResourceLocation(MOD_ID, "ruby_carpet_from_carpet"),
			) {
				it.pattern("ccc")
				it.pattern("crc")
				it.pattern("ccc")
				it.define('r', ItemRegistry.ruby)
				it.define('c', Items.WHITE_CARPET)
				it.group("carpet")
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Carpet (from wool)
		consumer
			.shaped(
				ItemRegistry.rubyCarpet,
				8,
				ResourceLocation(MOD_ID, "ruby_carpet_from_wool"),
			) {
				it.pattern("ww")
				it.define('w', ItemRegistry.rubyWool)
				it.group("carpet")
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Smelt ruby ore
		consumer
			.smelting(
				ItemRegistry.ruby,
				Ingredient.of(ItemRegistry.rubyOre),
				1f,
				100,
				ResourceLocation(MOD_ID, "ruby_ore_smelt"),
			) {
				it.unlockedBy("ruby_ore", hasItems(ItemRegistry.rubyOre))
			}

		// Blast ruby ore
		consumer
			.blasting(
				ItemRegistry.ruby,
				Ingredient.of(ItemRegistry.rubyOre),
				1f,
				100,
				ResourceLocation(MOD_ID, "ruby_ore_blast"),
			) {
				it.unlockedBy("ruby_ore", hasItems(ItemRegistry.rubyOre))
			}

		// Ruby Water Bucket
		consumer
			.shapeless(ItemRegistry.ghostWaterBucket) {
				it.requires(ItemRegistry.ruby)
				it.requires(Items.WATER_BUCKET)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Ruby Wool
		consumer
			.shapeless(ItemRegistry.rubyWool) {
				it.requires(ItemRegistry.ruby)
				it.requires(ItemTags.WOOL)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
			}

		// Logic Gate
		consumer
			.shaped(ItemRegistry.logicGate) {
				it.pattern("t t")
				it.pattern("srs")
				it.define('t', Items.REDSTONE_TORCH)
				it.define('s', ItemTags.STONE_CRAFTING_MATERIALS)
				it.define('r', ItemRegistry.ruby)
				it.unlockedBy("ruby", hasItems(ItemRegistry.ruby))
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

		return builder.save(this, id)
	}

	private fun Consumer<IFinishedRecipe>.shapeless(
		result: IItemProvider,
		resultCount: Int = 1,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (ShapelessRecipeBuilder) -> Unit,
	) {
		val builder = ShapelessRecipeBuilder(result, resultCount)
		addIngredients(builder)

		return builder.save(this, id)
	}

	private fun Consumer<IFinishedRecipe>.smelting(
		result: IItemProvider,
		ingredient: Ingredient,
		experience: Float,
		cookingTime: Int,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (CookingRecipeBuilder) -> Unit,
	) {
		val builder = CookingRecipeBuilder.smelting(ingredient, result, experience, cookingTime)
		addIngredients(builder)

		return builder.save(this, id)
	}

	private fun Consumer<IFinishedRecipe>.blasting(
		result: IItemProvider,
		ingredient: Ingredient,
		experience: Float,
		cookingTime: Int,
		id: ResourceLocation = ForgeRegistries.ITEMS.getKey(result.asItem())!!,
		addIngredients: (CookingRecipeBuilder) -> Unit,
	) {
		val builder = CookingRecipeBuilder.blasting(ingredient, result, experience, cookingTime)
		addIngredients(builder)

		return builder.save(this, id)
	}
}
