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

const val rubyBarrelTileEntitySize = 45

/**
 * The tile entity for [RubyBarrel].
 *
 * @author TheOnlyTails
 */
class RubyBarrelTileEntity : TileEntity(TileEntityTypes.rubyBarrel), INamedContainerProvider {
	private val optional = LazyOptional.of { itemHandler }
	val itemHandler = createHandler()
	var players = 0

	private fun createHandler() = object : ItemStackHandler(rubyBarrelTileEntitySize) {
		override fun onContentsChanged(slot: Int) {
			super.onContentsChanged(slot)
			setChanged()
		}
	}

	override fun getDisplayName() = TranslationTextComponent(blockState.block.descriptionId)

	override fun createMenu(id: Int, playerInventory: PlayerInventory, player: PlayerEntity) =
		RubyBarrelContainer(id, playerInventory, this)

	fun changeState(blockState: BlockState, value: Boolean) {
		if (blockState.block is RubyBarrel) {
			level?.setBlockAndUpdate(worldPosition, blockState.setValue(RubyBarrel.PROPERTY_OPEN, value))
		}
	}

	fun playSound(soundEvent: SoundEvent) {
		if (this.blockState.block is RubyBarrel) {
			val x = worldPosition.x + 0.5
			val y = worldPosition.y + 0.5
			val z = worldPosition.z + 0.5

			level?.playSound(
				null, x, y, z, soundEvent,
				SoundCategory.BLOCKS, 0.5f, level?.random?.nextFloat() ?: 0 * 0.1f + 0.9f
			)
		}
	}

	override fun save(nbt: CompoundNBT): CompoundNBT {
		optional.ifPresent { nbt.put("inv", it.serializeNBT()) }
		itemHandler.serializeNBT()

		return super.save(nbt)
	}

	override fun load(state: BlockState, nbt: CompoundNBT) {
		optional.ifPresent { it.deserializeNBT(nbt.getCompound("inv")) }
		itemHandler.deserializeNBT(nbt.getCompound("inv"))

		super.load(state, nbt)
	}

	override fun getUpdateTag(): CompoundNBT = super.save(CompoundNBT())

	override fun getUpdatePacket(): SUpdateTileEntityPacket {
		val nbt = CompoundNBT()
		save(nbt)
		return SUpdateTileEntityPacket(worldPosition, 0, nbt)
	}

	override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) = load(this.blockState, pkt.tag)

	override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> =
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) optional.cast()
		else super.getCapability(cap, side)

	override fun setRemoved() {
		super.setRemoved()
		optional.invalidate()
	}
}