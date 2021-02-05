package com.theonlytails.rubymod.tileentities

import com.theonlytails.rubymod.blocks.RubyBarrelBlock
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
 * The tile entity for [RubyBarrelBlock].
 *
 * @author TheOnlyTails
 */
class RubyBarrelTileEntity : TileEntity(TileEntityTypes.RUBY_BARREL), INamedContainerProvider {
	private val optional = LazyOptional.of { itemHandler }
	var size = 45
	val itemHandler = createHandler(size)
	var players = 0

	private fun createHandler(size: Int): ItemStackHandler {
		return object : ItemStackHandler(size) {
			override fun onContentsChanged(slot: Int) {
				super.onContentsChanged(slot)
				markDirty()
			}
		}
	}

	override fun getDisplayName() = TranslationTextComponent(blockState.block.translationKey)

	override fun createMenu(id: Int, playerInventory: PlayerInventory, player: PlayerEntity) =
		RubyBarrelContainer(id, playerInventory, this)

	fun changeState(blockState: BlockState, value: Boolean) {
		if (blockState.block is RubyBarrelBlock) {
			world?.setBlockState(pos, blockState.with(RubyBarrelBlock.PROPERTY_OPEN, value))
		}
	}

	fun playSound(soundEvent: SoundEvent) {
		if (this.blockState.block is RubyBarrelBlock) {
			val x = pos.x + 0.5
			val y = pos.y + 0.5
			val z = pos.z + 0.5

			world?.playSound(null, x, y, z, soundEvent,
				SoundCategory.BLOCKS, 0.5f, world?.rand?.nextFloat() ?: 0 * 0.1f + 0.9f)
		}
	}

	override fun write(nbt: CompoundNBT): CompoundNBT {
		optional.ifPresent { handler: ItemStackHandler ->
			nbt.put("inv",
				handler.serializeNBT())
		}

		itemHandler.serializeNBT()
		return super.write(nbt)
	}

	override fun read(state: BlockState, nbt: CompoundNBT) {
		optional.ifPresent { handler: ItemStackHandler ->
			handler.deserializeNBT(nbt.getCompound("inv"))
		}
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

	override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
		return if (cap === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			optional.cast()
		} else super.getCapability(cap, side)
	}

	override fun remove() {
		super.remove()
		optional.invalidate()
	}
}