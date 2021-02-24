package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.containers.RubyBarrelContainer
import net.minecraft.inventory.container.ContainerType
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom containers.
 *
 * @author TheOnlyTails
 */
object ContainerTypeRegistry {
	val containerTypes = KDeferredRegister(ForgeRegistries.CONTAINERS, MOD_ID)

	val rubyBarrel: ContainerType<RubyBarrelContainer> by containerTypes.registerObject("ruby_barrel") {
		IForgeContainerType.create { windowId, playerInventory, data ->
			RubyBarrelContainer(windowId,
				playerInventory,
				data)
		}
	}
}
