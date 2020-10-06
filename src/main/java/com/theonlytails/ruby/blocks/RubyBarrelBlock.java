package com.theonlytails.ruby.blocks;

import com.theonlytails.ruby.init.TileEntityTypes;
import com.theonlytails.ruby.tileentity.TileRubyBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

@SuppressWarnings("deprecation")
public class RubyBarrelBlock extends Block {
    public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;

    public RubyBarrelBlock() {
        super(Properties.create(
                Material.IRON, MaterialColor.RED)
                .hardnessAndResistance(3.5f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .setRequiresTool());

        this.setDefaultState(this.getStateContainer().getBaseState().with(PROPERTY_OPEN, false));
    }

    private static void dropItems(TileRubyBarrel rubyBarrel, World world, BlockPos pos) {
        IntStream.range(0, rubyBarrel.itemHandler.getSlots())
                .mapToObj(rubyBarrel.itemHandler::getStackInSlot)
                .filter(stack -> !stack.isEmpty())
                .forEach(stack ->
                        InventoryHelper.spawnItemStack(
                                world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            INamedContainerProvider tileEntity = (INamedContainerProvider) worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileRubyBarrel) {
                NetworkHooks.openGui((ServerPlayerEntity) player, tileEntity, pos);
                player.addStat(Stats.OPEN_BARREL);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileRubyBarrel) {
                TileRubyBarrel rubyBarrelTile = (TileRubyBarrel) tileEntity;

                dropItems(rubyBarrelTile, worldIn, pos);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityTypes.RUBY_BARREL.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        TileEntity rubyBarrel = worldIn.getTileEntity(pos);
        return rubyBarrel instanceof TileRubyBarrel ?
                ItemHandlerHelper.calcRedstoneFromInventory(((TileRubyBarrel) rubyBarrel).itemHandler) : 0;
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROPERTY_OPEN);
    }
}
