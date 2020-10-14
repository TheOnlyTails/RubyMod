package com.github.theonlytails.rubymod.tileentities;

import com.github.theonlytails.rubymod.blocks.RubyBarrelBlock;
import com.github.theonlytails.rubymod.containers.RubyBarrelContainer;
import com.github.theonlytails.rubymod.registries.TileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RubyBarrelTileEntity extends TileEntity implements INamedContainerProvider {
    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.itemHandler);
    public int size = 45;
    public final ItemStackHandler itemHandler = createHandler(size);
    public int players = 0;

    public RubyBarrelTileEntity() {
        super(TileEntityTypes.RUBY_BARREL.get());
    }

    private ItemStackHandler createHandler(int size) {
        return new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                markDirty();
            }
        };
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(this.getBlockState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new RubyBarrelContainer(id, playerInventory, this);
    }

    public void changeState(BlockState blockState, boolean value) {
        if (blockState.getBlock() instanceof RubyBarrelBlock) {
            if (world != null) {
                this.world.setBlockState(this.pos, blockState.with(RubyBarrelBlock.PROPERTY_OPEN, value));
            }
        }
    }

    public void playSound(SoundEvent soundEvent) {
        if (this.getBlockState().getBlock() instanceof RubyBarrelBlock) {
            double x = this.pos.getX() + 0.5D;
            double y = this.pos.getY() + 0.5D;
            double z = this.pos.getZ() + 0.5D;
            if (this.world != null) {
                this.world.playSound(null, x, y, z, soundEvent,
                        SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
    }


    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        optional.ifPresent(handler -> nbt.put("inv", handler.serializeNBT()));
        itemHandler.serializeNBT();
        return super.write(nbt);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        optional.ifPresent(handler -> handler.deserializeNBT(nbt.getCompound("inv")));
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return super.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);

        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return optional.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void remove() {
        super.remove();
        this.optional.invalidate();
    }
}
