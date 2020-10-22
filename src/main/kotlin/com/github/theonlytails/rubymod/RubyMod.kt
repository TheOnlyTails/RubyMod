@file:Suppress("DEPRECATION")

package com.github.theonlytails.rubymod

import com.github.theonlytails.rubymod.client.gui.RubyBarrelScreen
import com.github.theonlytails.rubymod.client.render.RubySheepRenderer
import com.github.theonlytails.rubymod.containers.RubyBarrelContainer
import com.github.theonlytails.rubymod.entities.RubySheepEntity
import com.github.theonlytails.rubymod.registries.*
import com.github.theonlytails.rubymod.world.FeatureGen
import net.minecraft.block.ComposterBlock
import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.DeferredWorkQueue
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.KotlinModLoadingContext.Companion.get
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
@Mod("rubymod")
object RubyMod {

	const val MOD_ID = "rubymod"
	val RUBY_TAB: ItemGroup = object : ItemGroup("ruby_tab") {
		override fun createIcon(): ItemStack {
			return ItemStack(ItemRegistry.RUBY)
		}
	}
	val RUBY_TAB_PROPERTY: Item.Properties = Item.Properties().group(RUBY_TAB)

	@Suppress("unused")
	val LOGGER: Logger = LogManager.getLogger()

	init {
		val modEventBus = get().getKEventBus()

		MOD_BUS.addListener(::setup)
		MOD_BUS.addListener(::doClientStuff)

		FORGE_BUS.register(FeatureGen)
		FORGE_BUS.addListener(EventPriority.HIGH, ::biomeLoading)

		EntityTypeRegistry.ENTITY_TYPES.register(modEventBus)
		BiomeRegistry.BIOMES.register(modEventBus)
		FluidRegistry.FLUIDS.register(modEventBus)
		TileEntityTypes.TILE_ENTITIES.register(modEventBus)
		ContainerTypeRegistry.CONTAINER_TYPES.register(modEventBus)
		EnchantmentRegistry.ENCHANTMENTS.register(modEventBus)
		BlockRegistry.BLOCKS.register(modEventBus)
		ItemRegistry.ITEMS.register(modEventBus)
		PotionRegistry.POTIONS.register(modEventBus)

		MinecraftForge.EVENT_BUS.register(this)
	}

	@Suppress("UNUSED_PARAMETER")
	private fun setup(event: FMLCommonSetupEvent) {
		event.enqueueWork {
			GlobalEntityTypeAttributes.put(
				EntityTypeRegistry.RUBY_SHEEP,
				RubySheepEntity.setCustomAttributes().create())

			FeatureGen.registerFeatures(event)

			ComposterBlock.CHANCES[ItemRegistry.POISONED_APPLE.asItem()] = 0.3f

			FluidRegistry.FLUIDS.registry.entries.forEach {
				RenderTypeLookup.setRenderLayer(it.value, RenderType.getTranslucent())
			}
		}
	}

	@Suppress("UNUSED_PARAMETER")
	private fun doClientStuff(event: FMLClientSetupEvent) {
		DeferredWorkQueue.runLater {
			ScreenManager.registerFactory(ContainerTypeRegistry.RUBY_BARREL) {
					screenContainer: RubyBarrelContainer,
					inv: PlayerInventory,
					titleIn: ITextComponent,
				->
				RubyBarrelScreen(screenContainer, inv, titleIn)
			}
		}

		RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.RUBY_SHEEP) { renderManagerIn: EntityRendererManager ->
			RubySheepRenderer(renderManagerIn)
		}
	}

	private fun biomeLoading(event: BiomeLoadingEvent) {
		FeatureGen.onBiomeLoading(event)
		BiomeRegistry.biomeLoading(event)
	}
}