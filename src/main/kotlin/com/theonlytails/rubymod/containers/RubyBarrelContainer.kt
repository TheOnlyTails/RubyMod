package com.theonlytails.rubymod.containers

import com.theonlytails.rubymod.blocks.RubyBarrel
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ContainerTypeRegistry
import com.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import com.theonlytails.rubymod.tileentities.rubyBarrelTileEntitySize
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

/**
 * The container class for [RubyBarrel].
 *
 * @author TheOnlyTails
 */
class RubyBarrelContainer(
	id: Int,
	playerInventory: PlayerInventory,
	private val tileEntity: RubyBarrelTileEntity,
) : Container(ContainerTypeRegistry.rubyBarrel, id) {
	private val canInteractWithCallable: IWorldPosCallable

	init {
		val world = tileEntity.level ?: throw NullPointerException("The world was null, for some reason.")
		canInteractWithCallable = IWorldPosCallable.create(world, tileEntity.blockPos)

		tileEntity.players++
		tileEntity.playSound(SoundEvents.BARREL_OPEN)
		tileEntity.changeState(tileEntity.blockState, true)

		// Main barrel inventory
		val startX = 8
		val startY = 18
		val slotSizePlus2 = 18

		for (row in 0..4) {
			for (column in 0..8) {
				addSlot(
					SlotItemHandler(
						tileEntity.itemHandler,
						row * 9 + column,
						startX + column * slotSizePlus2,
						startY + row * slotSizePlus2
					)
				)
			}
		}

		// Main Player inventory
		val playerInvStartY = startY * 5 + 32
		for (row in 0..2)
			for (column in 0..8)
				addSlot(
					Slot(
						playerInventory,
						9 + row * 9 + column,
						startX + column * slotSizePlus2,
						playerInvStartY + row * slotSizePlus2
					)
				)

		// Hotbar
		val hotbarY = playerInvStartY + playerInvStartY / 2 - 3
		for (column in 0..8)
			addSlot(
				Slot(
					playerInventory,
					column,
					startX + column * slotSizePlus2,
					hotbarY
				)
			)
	}

	constructor(windowId: Int, playerInventory: PlayerInventory, data: PacketBuffer) :
			this(windowId, playerInventory, getTileEntity(playerInventory, data))

	override fun removed(playerIn: PlayerEntity) {
		super.removed(playerIn)
		tileEntity.apply {
			players--
			playSound(SoundEvents.BARREL_CLOSE)
			changeState(tileEntity.blockState, false)
		}
	}

	override fun stillValid(playerIn: PlayerEntity): Boolean {
		return stillValid(
			canInteractWithCallable, playerIn, BlockRegistry.rubyBarrel
		)
	}

	override fun quickMoveStack(playerIn: PlayerEntity, index: Int): ItemStack {
		var itemStack = ItemStack.EMPTY
		val slot = slots[index]

		if (slot != null && slot.hasItem()) {
			val itemStack1 = slot.item
			itemStack = itemStack1.copy()

			if (index < rubyBarrelTileEntitySize) {
				if (!moveItemStackTo(itemStack1, 5 * 9, slots.size, true)) return ItemStack.EMPTY

			} else if (!moveItemStackTo(itemStack1, 0, 5 * 9, false)) return ItemStack.EMPTY

			if (itemStack1.isEmpty) slot.set(ItemStack.EMPTY) else slot.setChanged()
		}

		return itemStack
	}

	companion object {
		private fun getTileEntity(playerInventory: PlayerInventory, data: PacketBuffer): RubyBarrelTileEntity {
			Objects.requireNonNull(playerInventory, "playerInventory cannot be null")
			Objects.requireNonNull(data, "data cannot be null")

			val tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos())

			if (tileAtPos is RubyBarrelTileEntity) return tileAtPos
			else throw IllegalStateException("Tile entity is not correct! $tileAtPos")
		}
	}
}
