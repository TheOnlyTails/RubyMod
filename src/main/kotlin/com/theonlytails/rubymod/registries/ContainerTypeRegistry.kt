package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.containers.RubyBarrelContainer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ContainerType
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom containers.
 *
 * @author TheOnlyTails
 */
object ContainerTypeRegistry {
	val CONTAINER_TYPES = KDeferredRegister(ForgeRegistries.CONTAINERS, com.theonlytails.rubymod.RubyMod.MOD_ID)

	val RUBY_BARREL: ContainerType<RubyBarrelContainer> by CONTAINER_TYPES.registerObject("ruby_barrel") {
		IForgeContainerType.create { windowId: Int, playerInventory: PlayerInventory, data: PacketBuffer ->
			RubyBarrelContainer(windowId,
				playerInventory,
				data)
		}
	}
}
