@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod

import com.theonlytails.rubymod.client.gui.RubyBarrelScreen
import com.theonlytails.rubymod.client.render.RubySheepRenderer
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.events.ModEvents
import com.theonlytails.rubymod.items.CustomSpawnEggItem
import com.theonlytails.rubymod.registries.*
import com.theonlytails.rubymod.trades.TradesRegisterer
import com.theonlytails.rubymod.world.FeatureGen
import net.minecraft.block.ComposterBlock
import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.event.RegistryEvent
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
		MOD_BUS.addListener(RubyMod::setup)
		MOD_BUS.addListener(RubyMod::doClientStuff)

		FORGE_BUS.register(this)
		FORGE_BUS.addListener(TradesRegisterer::addVillagerTrades)
		FORGE_BUS.addGenericListener(RubyMod::onRegisterEntities)
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

		RenderTypeLookup.setRenderLayer(BlockRegistry.LOGIC_GATE, RenderType.getCutout())

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.RUBY_SHEEP) {
			RubySheepRenderer(it)
		}
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onRegisterEntities(event: RegistryEvent.Register<EntityType<*>>) {
		CustomSpawnEggItem.initSpawnEggs()
	}

	fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
}