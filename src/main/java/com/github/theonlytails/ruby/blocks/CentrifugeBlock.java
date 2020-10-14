package com.github.theonlytails.ruby.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class CentrifugeBlock extends DirectionalBlock {
    public static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 1, 3, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 3, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 3, 1),
            Block.makeCuboidShape(15, 0, 15, 16, 3, 16),
            Block.makeCuboidShape(0, 3, 0, 16, 13, 16),
            Block.makeCuboidShape(0, 13, 0, 1, 15, 16),
            Block.makeCuboidShape(15, 13, 0, 16, 15, 16),
            Block.makeCuboidShape(1, 13, 15, 15, 15, 16),
            Block.makeCuboidShape(1, 13, 0, 15, 15, 1)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(15, 0, 0, 16, 3, 1),
            Block.makeCuboidShape(0, 0, 0, 1, 3, 1),
            Block.makeCuboidShape(15, 0, 15, 16, 3, 16),
            Block.makeCuboidShape(0, 0, 15, 1, 3, 16),
            Block.makeCuboidShape(0, 3, 0, 16, 13, 16),
            Block.makeCuboidShape(0, 13, 0, 16, 15, 1),
            Block.makeCuboidShape(0, 13, 15, 16, 15, 16),
            Block.makeCuboidShape(0, 13, 1, 1, 15, 15),
            Block.makeCuboidShape(15, 13, 1, 16, 15, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(15, 0, 15, 16, 3, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 3, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 3, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 3, 1),
            Block.makeCuboidShape(0, 3, 0, 16, 13, 16),
            Block.makeCuboidShape(15, 13, 0, 16, 15, 16),
            Block.makeCuboidShape(0, 13, 0, 1, 15, 16),
            Block.makeCuboidShape(1, 13, 0, 15, 15, 1),
            Block.makeCuboidShape(1, 13, 15, 15, 15, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 15, 1, 3, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 3, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 3, 1),
            Block.makeCuboidShape(15, 0, 0, 16, 3, 1),
            Block.makeCuboidShape(0, 3, 0, 16, 13, 16),
            Block.makeCuboidShape(0, 13, 15, 16, 15, 16),
            Block.makeCuboidShape(0, 13, 0, 16, 15, 1),
            Block.makeCuboidShape(15, 13, 1, 16, 15, 15),
            Block.makeCuboidShape(0, 13, 1, 1, 15, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public CentrifugeBlock() {
        super(AbstractBlock.Properties.create(Material.IRON)
                .hardnessAndResistance(3.5f, 5f)
                .sound(SoundType.ANVIL)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }
}