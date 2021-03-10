package com.theonlytails.rubymod

import com.theonlytails.rubymod.client.gui.RubyBarrelScreen
import com.theonlytails.rubymod.client.render.RubySheepRenderer
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.items.CustomSpawnEggItem
import com.theonlytails.rubymod.registries.*
import com.theonlytails.rubymod.trades.TradesRegisterer
import com.theonlytails.rubymod.util.BrewingRecipes
import com.theonlytails.rubymod.world.FeatureGen
import net.minecraft.block.ComposterBlock
import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderTypeLookup
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import kotlin.collections.set

const val MOD_ID = "rubymod"

val rubyTab: ItemGroup = object : ItemGroup("ruby_tab") {
    override fun makeIcon() = ItemStack(ItemRegistry.ruby)
}

val rubyTabProperty: Item.Properties = Item.Properties().tab(rubyTab)

fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)

val logger: Logger = LogManager.getLogger()


/**
 * The main mod class.
 *
 * @author TheOnlyTails
 */
@Mod(MOD_ID)
object RubyMod {
    init {
        MOD_BUS.addListener(::commonSetup)
        MOD_BUS.addListener(::clientSetup)
        MOD_BUS.addListener(::createEntityAttributes)

        FORGE_BUS.addListener(TradesRegisterer::addVillagerTrades)
        FORGE_BUS.addGenericListener(::onRegisterEntities)
        FORGE_BUS.addListener(EventPriority.HIGH, BiomeRegistry::biomeLoading)
        FORGE_BUS.addListener(EventPriority.HIGH, FeatureGen::addFeaturesToBiomes)

        EntityTypeRegistry.entityTypes.register(MOD_BUS)
        BiomeRegistry.biomes.register(MOD_BUS)
        FluidRegistry.fluids.register(MOD_BUS)
        TileEntityTypes.tileEntities.register(MOD_BUS)
        ContainerTypeRegistry.containerTypes.register(MOD_BUS)
        EnchantmentRegistry.enchantments.register(MOD_BUS)
        BlockRegistry.blocks.register(MOD_BUS)
        ItemRegistry.items.register(MOD_BUS)
        PotionRegistry.potions.register(MOD_BUS)
        VillagerProfessionsRegistry.professions.register(MOD_BUS)
        VillagerProfessionsRegistry.pointsOfInterest.register(MOD_BUS)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            PointOfInterestType.registerBlockStates(VillagerProfessionsRegistry.jewelerPOI)

            BrewingRecipes.registerBrewingRecipes(event)

            ComposterBlock.COMPOSTABLES[ItemRegistry.poisonedApple.asItem()] = 0.3f

            FluidRegistry.fluids.registry.entries.forEach {
                RenderTypeLookup.setRenderLayer(it.value, RenderType.translucent())
            }
        }
    }

    private fun clientSetup(event: FMLClientSetupEvent) {
        event.enqueueWork {
            ScreenManager.register(ContainerTypeRegistry.rubyBarrel) { screenContainer, inv, titleIn ->
                RubyBarrelScreen(screenContainer, inv, titleIn)
            }
        }

        RenderTypeLookup.setRenderLayer(BlockRegistry.logicGate, RenderType.cutout())

        RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegistry.rubySheep) { RubySheepRenderer(it) }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onRegisterEntities(event: RegistryEvent.Register<EntityType<*>>) = CustomSpawnEggItem.initSpawnEggs()

    private fun createEntityAttributes(event: EntityAttributeCreationEvent) =
        event.put(EntityTypeRegistry.rubySheep, RubySheepEntity.setCustomAttributes())
}
