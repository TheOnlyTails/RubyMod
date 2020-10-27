package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.containers.RubyBarrelContainer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ContainerType
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ContainerTypeRegistry {
	val CONTAINER_TYPES = KDeferredRegister(ForgeRegistries.CONTAINERS, RubyMod.MOD_ID)

	val RUBY_BARREL: ContainerType<RubyBarrelContainer> by CONTAINER_TYPES.register("ruby_barrel") {
		IForgeContainerType.create { windowId: Int, playerInventory: PlayerInventory, data: PacketBuffer ->
			RubyBarrelContainer(windowId,
				playerInventory,
				data)
		}
	}
}