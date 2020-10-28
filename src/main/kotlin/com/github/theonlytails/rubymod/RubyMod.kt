@file:Suppress("DEPRECATION")

package com.github.theonlytails.rubymod

import com.github.theonlytails.rubymod.client.gui.RubyBarrelScreen
import com.github.theonlytails.rubymod.client.render.RubySheepRenderer
import com.github.theonlytails.rubymod.entities.RubySheepEntity
import com.github.theonlytails.rubymod.events.ModEvents
import com.github.theonlytails.rubymod.items.CustomSpawnEggItem
import com.github.theonlytails.rubymod.registries.*
import com.github.theonlytails.rubymod.trades.ItemsForRubyAndItemsTrade
import com.github.theonlytails.rubymod.trades.ItemsForRubyTrade
import com.github.theonlytails.rubymod.world.FeatureGen
import net.minecraft.block.ComposterBlock
import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes
import net.minecraft.item.*
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.DeferredWorkQueue
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import kotlin.collections.set

/**
 * The main mod class.
 *
 * @author TheOnlyTails
 */
@Mod("rubymod")
object RubyMod {
	const val MOD_ID = "rubymod"

	val RUBY_TAB: ItemGroup = object : ItemGroup("ruby_tab") {
		override fun createIcon() = ItemStack(ItemRegistry.RUBY)
	}

	val RUBY_TAB_PROPERTY: Item.Properties = Item.Properties().group(RUBY_TAB)

	@Suppress("unused")
	val LOGGER: Logger = LogManager.getLogger()

	init {
		MOD_BUS.addListener(::setup)
		MOD_BUS.addListener(::doClientStuff)

		FORGE_BUS.register(this)
		FORGE_BUS.addListener(::addVillagerTrades)
		FORGE_BUS.addGenericListener(::onRegisterEntities)
		FORGE_BUS.addListener(EventPriority.HIGH, BiomeRegistry::biomeLoading)
		FORGE_BUS.addListener(EventPriority.HIGH, FeatureGen::addFeaturesToBiomes)

		EntityTypeRegistry.ENTITY_TYPES.register(MOD_BUS)
		BiomeRegistry.BIOMES.register(MOD_BUS)
		FluidRegistry.FLUIDS.register(MOD_BUS)
		TileEntityTypes.TILE_ENTITIES.register(MOD_BUS)
		ContainerTypeRegistry.CONTAINER_TYPES.register(MOD_BUS)
		EnchantmentRegistry.ENCHANTMENTS.register(MOD_BUS)
		BlockRegistry.BLOCKS.register(MOD_BUS)
		ItemRegistry.ITEMS.register(MOD_BUS)
		PotionRegistry.POTIONS.register(MOD_BUS)
		VillagerProfessionsRegistry.PROFESSIONS.register(MOD_BUS)
		VillagerProfessionsRegistry.POINTS_OF_INTEREST.register(MOD_BUS)
	}

	@Suppress("UNUSED_PARAMETER")
	private fun setup(event: FMLCommonSetupEvent) {
		event.enqueueWork {
			GlobalEntityTypeAttributes.put(
				EntityTypeRegistry.RUBY_SHEEP,
				RubySheepEntity.setCustomAttributes().create())

			FeatureGen.registerConfiguredFeatures(event)

			PointOfInterestType.registerBlockStates(VillagerProfessionsRegistry.JEWELER_POI)

			ModEvents.registerBrewingRecipes(event)

			ComposterBlock.CHANCES[ItemRegistry.POISONED_APPLE.asItem()] = 0.3f

			FluidRegistry.FLUIDS.registry.entries.forEach {
				RenderTypeLookup.setRenderLayer(it.value, RenderType.getTranslucent())
			}
		}
	}

	@Suppress("UNUSED_PARAMETER")
	private fun doClientStuff(event: FMLClientSetupEvent) {
		DeferredWorkQueue.runLater {
			ScreenManager.registerFactory(ContainerTypeRegistry.RUBY_BARREL) { screenContainer, inv, titleIn ->
				RubyBarrelScreen(screenContainer, inv, titleIn)
			}
		}

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.RUBY_SHEEP) {
			RubySheepRenderer(it)
		}
	}

	private fun addVillagerTrades(event: VillagerTradesEvent) {
		if (event.type == VillagerProfessionsRegistry.JEWELER) {
			// Level 1 trades
			event.trades[1].add(ItemsForRubyTrade(Items.COAL, 45, maxUses = 12))
			event.trades[1].add(ItemsForRubyTrade(Items.IRON_NUGGET, 16, maxUses = 9))
			event.trades[1].add(ItemsForRubyTrade(Items.IRON_INGOT, 3, maxUses = 8))
			event.trades[1].add(ItemsForRubyTrade(Items.GOLD_NUGGET, 16, maxUses = 9))

			// Level 2 trades
			event.trades[2].add(ItemsForRubyTrade(Items.GOLD_INGOT, 4, maxUses = 10))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_PICKAXE,
				sellingItem = ItemRegistry.RUBY_PICKAXE,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_SWORD,
				sellingItem = ItemRegistry.RUBY_SWORD,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_AXE,
				sellingItem = ItemRegistry.RUBY_AXE,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_SHOVEL,
				sellingItem = ItemRegistry.RUBY_SHOVEL,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_HOE,
				sellingItem = ItemRegistry.RUBY_HOE,
				maxUses = 1,
				xpValue = 5,
			))

			// Level 3 trades
			event.trades[3].add(ItemsForRubyTrade(Items.EMERALD_BLOCK, sellingItemCount = 10, maxUses = 8))

			// Level 4 trades
			event.trades[4].add(ItemsForRubyTrade(Items.DIAMOND, sellingItemCount = 2, maxUses = 2))

			// Level 5 trades
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_HELMET,
				sellingItem = ItemRegistry.RUBY_HELMET,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_CHESTPLATE,
				sellingItem = ItemRegistry.RUBY_CHESTPLATE,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_LEGGINGS,
				sellingItem = ItemRegistry.RUBY_LEGGINGS,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_BOOTS,
				sellingItem = ItemRegistry.RUBY_BOOTS,
				maxUses = 1,
			))
		}
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onRegisterEntities(event: RegistryEvent.Register<EntityType<*>>) {
		CustomSpawnEggItem.initSpawnEggs()
	}
}