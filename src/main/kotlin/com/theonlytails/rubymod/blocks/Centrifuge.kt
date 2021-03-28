@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.DirectionalBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.util.Direction
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.IBooleanFunction
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes
import net.minecraft.world.IBlockReader
import net.minecraftforge.common.ToolType
import javax.annotation.Nonnull

/**
 * The block class for the centrifuge block.
 *
 * @author TheOnlyTails
 */
class Centrifuge : HorizontalBlock(
	Properties.of(Material.METAL)
		.strength(3.5f, 5f)
		.sound(SoundType.ANVIL)
		.harvestLevel(2)
		.harvestTool(ToolType.PICKAXE)
		.requiresCorrectToolForDrops()
) {
	override fun getStateForPlacement(context: BlockItemUseContext): BlockState =
		defaultBlockState().setValue(FACING, context.horizontalDirection.opposite)

    @Nonnull
    override fun rotate(state: BlockState, rot: Rotation): BlockState =
        state.setValue(FACING, rot.rotate(state.getValue(FACING)))

    @Nonnull
    override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState =
        state.rotate(mirrorIn.getRotation(state.getValue(FACING)))

    override fun createBlockStateDefinition(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    @Nonnull
    override fun getShape(
        state: BlockState,
        worldIn: IBlockReader,
        pos: BlockPos,
        context: ISelectionContext,
    ) = when (state.getValue(FACING)) {
        Direction.EAST -> SHAPE_E
        Direction.SOUTH -> SHAPE_S
        Direction.WEST -> SHAPE_W
        else -> SHAPE_N
    }

    companion object {
	    val SHAPE_N: VoxelShape = sequenceOf(
		    box(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
		    box(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
		    box(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
		    box(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
		    box(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
		    box(0.0, 13.0, 0.0, 1.0, 15.0, 16.0),
		    box(15.0, 13.0, 0.0, 16.0, 15.0, 16.0),
		    box(1.0, 13.0, 15.0, 15.0, 15.0, 16.0),
		    box(1.0, 13.0, 0.0, 15.0, 15.0, 1.0)
	    ).reduce { v1, v2 -> VoxelShapes.join(v1, v2, IBooleanFunction.OR) }

	    val SHAPE_E: VoxelShape = sequenceOf(
		    box(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
		    box(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
		    box(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
		    box(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
		    box(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
		    box(0.0, 13.0, 0.0, 16.0, 15.0, 1.0),
		    box(0.0, 13.0, 15.0, 16.0, 15.0, 16.0),
		    box(0.0, 13.0, 1.0, 1.0, 15.0, 15.0),
		    box(15.0, 13.0, 1.0, 16.0, 15.0, 15.0)
	    ).reduce { v1, v2 -> VoxelShapes.join(v1, v2, IBooleanFunction.OR) }

	    val SHAPE_S: VoxelShape = sequenceOf(
		    box(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
		    box(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
		    box(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
		    box(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
		    box(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
		    box(15.0, 13.0, 0.0, 16.0, 15.0, 16.0),
		    box(0.0, 13.0, 0.0, 1.0, 15.0, 16.0),
		    box(1.0, 13.0, 0.0, 15.0, 15.0, 1.0),
		    box(1.0, 13.0, 15.0, 15.0, 15.0, 16.0)
	    ).reduce { v1, v2 -> VoxelShapes.join(v1, v2, IBooleanFunction.OR) }

	    val SHAPE_W: VoxelShape = sequenceOf(
		    box(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
		    box(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
		    box(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
		    box(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
		    box(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
		    box(0.0, 13.0, 15.0, 16.0, 15.0, 16.0),
		    box(0.0, 13.0, 0.0, 16.0, 15.0, 1.0),
		    box(15.0, 13.0, 1.0, 16.0, 15.0, 15.0),
		    box(0.0, 13.0, 1.0, 1.0, 15.0, 15.0)
	    ).reduce { v1, v2 -> VoxelShapes.join(v1, v2, IBooleanFunction.OR) }
    }
}
