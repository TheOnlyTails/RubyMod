package com.github.theonlytails.ruby.containers;

import com.github.theonlytails.ruby.registries.BlockRegistry;
import com.github.theonlytails.ruby.registries.ContainerTypeRegistry;
import com.github.theonlytails.ruby.tileentities.RubyBarrelTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;

public class RubyBarrelContainer extends Container {
    public final RubyBarrelTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public RubyBarrelContainer(int id, PlayerInventory playerInventory, final RubyBarrelTileEntity tileEntity) {
        super(ContainerTypeRegistry.RUBY_BARREL.get(), id);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(
                Objects.requireNonNull(tileEntity.getWorld()),
                tileEntity.getPos()
        );

        tileEntity.players++;
        tileEntity.playSound(SoundEvents.BLOCK_BARREL_OPEN);
        tileEntity.changeState(tileEntity.getBlockState(), true);

        // Main barrel inventory
        int startX = 8;
        int startY = 18;
        int slotSizePlus2 = 18;

        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new SlotItemHandler(
                        tileEntity.itemHandler,
                        (row * 9) + column,
                        startX + (column * slotSizePlus2),
                        startY + (row * slotSizePlus2)
                ));
            }
        }

        // Main Player inventory
        int playerInvStartY = startY * 5 + 32;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(
                        playerInventory,
                        9 + (row * 9) + column,
                        startX + (column * slotSizePlus2),
                        playerInvStartY + (row * slotSizePlus2)
                ));
            }
        }

        // Hotbar
        int hotbarY = playerInvStartY + (playerInvStartY / 2) - 3;

        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(
                    playerInventory,
                    column,
                    startX + (column * slotSizePlus2),
                    hotbarY
            ));
        }
    }

    public RubyBarrelContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static RubyBarrelTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof RubyBarrelTileEntity) {
            return (RubyBarrelTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        tileEntity.players--;
        tileEntity.playSound(SoundEvents.BLOCK_BARREL_CLOSE);
        tileEntity.changeState(tileEntity.getBlockState(), false);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(
                canInteractWithCallable, playerIn, BlockRegistry.RUBY_BARREL.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemstack = itemStack1.copy();
            if (index < 5 * 9) {
                if (!this.mergeItemStack(itemStack1, 5 * 9, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, 5 * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
