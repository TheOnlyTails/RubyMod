package com.theonlytails.rubymod.tileentities

import com.theonlytails.rubymod.blocks.RubyBarrel
import com.theonlytails.rubymod.containers.RubyBarrelContainer
import com.theonlytails.rubymod.registries.TileEntityTypes
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Direction
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

/**
 * The tile entity for [RubyBarrel].
 *
 * @author TheOnlyTails
 */
class RubyBarrelTileEntity : TileEntity(TileEntityTypes.rubyBarrel), INamedContainerProvider {
	private val optional = LazyOptional.of { itemHandler }
	val size = 45
	val itemHandler = createHandler()
	var players = 0

	private fun createHandler() = object : ItemStackHandler(size) {
		override fun onContentsChanged(slot: Int) {
			super.onContentsChanged(slot)
			markDirty()
		}
	}

	override fun getDisplayName() = TranslationTextComponent(blockState.block.translationKey)

	override fun createMenu(id: Int, playerInventory: PlayerInventory, player: PlayerEntity) =
		RubyBarrelContainer(id, playerInventory, this)

	fun changeState(blockState: BlockState, value: Boolean) {
		if (blockState.block is RubyBarrel) {
			world?.setBlockState(pos, blockState.with(RubyBarrel.PROPERTY_OPEN, value))
		}
	}

	fun playSound(soundEvent: SoundEvent) {
		if (this.blockState.block is RubyBarrel) {
			val x = pos.x + 0.5
			val y = pos.y + 0.5
			val z = pos.z + 0.5

			world?.playSound(null, x, y, z, soundEvent,
				SoundCategory.BLOCKS, 0.5f, world?.rand?.nextFloat() ?: 0 * 0.1f + 0.9f)
		}
	}

	override fun write(nbt: CompoundNBT): CompoundNBT {
		optional.ifPresent { nbt.put("inv", it.serializeNBT()) }
		itemHandler.serializeNBT()

		return super.write(nbt)
	}

	override fun read(state: BlockState, nbt: CompoundNBT) {
		optional.ifPresent { it.deserializeNBT(nbt.getCompound("inv")) }
		itemHandler.deserializeNBT(nbt.getCompound("inv"))

		super.read(state, nbt)
	}

	override fun getUpdateTag(): CompoundNBT = super.write(CompoundNBT())

	override fun getUpdatePacket(): SUpdateTileEntityPacket {
		val nbt = CompoundNBT()
		write(nbt)
		return SUpdateTileEntityPacket(pos, 0, nbt)
	}

	override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) =
		read(this.blockState, pkt.nbtCompound)

	override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) optional.cast()
		else super.getCapability(cap, side)

	override fun remove() {
		super.remove()
		optional.invalidate()
	}
}
