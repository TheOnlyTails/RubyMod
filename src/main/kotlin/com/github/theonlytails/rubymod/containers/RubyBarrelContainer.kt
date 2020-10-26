package com.github.theonlytails.rubymod.containers

import com.github.theonlytails.rubymod.registries.BlockRegistry
import com.github.theonlytails.rubymod.registries.ContainerTypeRegistry
import com.github.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.IWorldPosCallable
import net.minecraft.util.SoundEvents
import net.minecraftforge.items.SlotItemHandler
import java.util.Objects

class RubyBarrelContainer(
	id: Int,
	playerInventory: PlayerInventory,
	private val tileEntity: RubyBarrelTileEntity,
) :
	Container(ContainerTypeRegistry.RUBY_BARREL, id) {

	private val canInteractWithCallable: IWorldPosCallable

	init {
		val world = tileEntity.world ?: throw NullPointerException("The world was null, for some reason.")
		canInteractWithCallable = IWorldPosCallable.of(
			world,
			tileEntity.pos,
		)

		tileEntity.players++
		tileEntity.playSound(SoundEvents.BLOCK_BARREL_OPEN)
		tileEntity.changeState(tileEntity.blockState, true)

		// Main barrel inventory
		val startX = 8
		val startY = 18
		val slotSizePlus2 = 18
		for (row in 0..4) {
			for (column in 0..8) {
				addSlot(SlotItemHandler(
					tileEntity.itemHandler,
					row * 9 + column,
					startX + column * slotSizePlus2,
					startY + row * slotSizePlus2
				))
			}
		}

		// Main Player inventory
		val playerInvStartY = startY * 5 + 32
		for (row in 0..2) {
			for (column in 0..8) {
				addSlot(Slot(
					playerInventory,
					9 + row * 9 + column,
					startX + column * slotSizePlus2,
					playerInvStartY + row * slotSizePlus2
				))
			}
		}

		// Hotbar
		val hotbarY = playerInvStartY + playerInvStartY / 2 - 3
		for (column in 0..8) {
			addSlot(Slot(
				playerInventory,
				column,
				startX + column * slotSizePlus2,
				hotbarY
			))
		}
	}

	constructor(windowId: Int, playerInventory: PlayerInventory, data: PacketBuffer) :
			this(windowId, playerInventory, getTileEntity(playerInventory, data))

	override fun onContainerClosed(playerIn: PlayerEntity) {
		super.onContainerClosed(playerIn)
		tileEntity.players--
		tileEntity.playSound(SoundEvents.BLOCK_BARREL_CLOSE)
		tileEntity.changeState(tileEntity.blockState, false)
	}

	override fun canInteractWith(playerIn: PlayerEntity): Boolean {
		return isWithinUsableDistance(
			canInteractWithCallable, playerIn, BlockRegistry.RUBY_BARREL)
	}

	override fun transferStackInSlot(playerIn: PlayerEntity, index: Int): ItemStack {
		var itemStack = ItemStack.EMPTY
		val slot = inventorySlots[index]
		if (slot != null && slot.hasStack) {
			val itemStack1 = slot.stack
			itemStack = itemStack1.copy()
			if (index < tileEntity.size) {
				if (!mergeItemStack(itemStack1, 5 * 9, inventorySlots.size, true)) {
					return ItemStack.EMPTY
				}
			} else if (!mergeItemStack(itemStack1, 0, 5 * 9, false)) {
				return ItemStack.EMPTY
			}
			if (itemStack1.isEmpty) {
				slot.putStack(ItemStack.EMPTY)
			} else {
				slot.onSlotChanged()
			}
		}
		return itemStack
	}

	companion object {
		private fun getTileEntity(playerInventory: PlayerInventory, data: PacketBuffer): RubyBarrelTileEntity {
			Objects.requireNonNull(playerInventory, "playerInventory cannot be null")
			Objects.requireNonNull(data, "data cannot be null")
			val tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos())
			if (tileAtPos is RubyBarrelTileEntity) {
				return tileAtPos
			}
			throw IllegalStateException("Tile entity is not correct! $tileAtPos")
		}
	}

}