@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.blocks

import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.StateContainer
import net.minecraft.util.Direction
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.*
import net.minecraft.world.IBlockReader
import net.minecraftforge.common.ToolType
import java.util.stream.Stream
import javax.annotation.Nonnull

/**
 * The block class for the centrifuge block.
 *
 * @author TheOnlyTails
 */
class Centrifuge : DirectionalBlock(Properties.create(Material.IRON)
	.hardnessAndResistance(3.5f, 5f)
	.sound(SoundType.ANVIL)
	.harvestLevel(2)
	.harvestTool(ToolType.PICKAXE)
	.setRequiresTool()) {
	override fun getStateForPlacement(context: BlockItemUseContext): BlockState? =
		defaultState.with(FACING, context.placementHorizontalFacing.opposite)

	@Nonnull
	override fun rotate(state: BlockState, rot: Rotation): BlockState =
		state.with(FACING, rot.rotate(state.get(FACING)))

	@Nonnull
	override fun mirror(state: BlockState, mirrorIn: Mirror): BlockState =
		state.rotate(mirrorIn.toRotation(state.get(FACING)))

	override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
		builder.add(FACING)
	}

	@Nonnull
	override fun getShape(
		state: BlockState,
		worldIn: IBlockReader,
		pos: BlockPos,
		context: ISelectionContext,
	) = when (state.get(FACING)) {
		Direction.EAST -> SHAPE_E
		Direction.SOUTH -> SHAPE_S
		Direction.WEST -> SHAPE_W
		else -> SHAPE_N
	}

	companion object {
		val SHAPE_N = Stream.of(
			makeCuboidShape(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
			makeCuboidShape(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
			makeCuboidShape(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
			makeCuboidShape(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
			makeCuboidShape(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
			makeCuboidShape(0.0, 13.0, 0.0, 1.0, 15.0, 16.0),
			makeCuboidShape(15.0, 13.0, 0.0, 16.0, 15.0, 16.0),
			makeCuboidShape(1.0, 13.0, 15.0, 15.0, 15.0, 16.0),
			makeCuboidShape(1.0, 13.0, 0.0, 15.0, 15.0, 1.0)
		).reduce { v1: VoxelShape, v2: VoxelShape ->
			VoxelShapes.combineAndSimplify(v1,
				v2,
				IBooleanFunction.OR)
		}.get()

		val SHAPE_E = Stream.of(
			makeCuboidShape(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
			makeCuboidShape(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
			makeCuboidShape(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
			makeCuboidShape(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
			makeCuboidShape(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
			makeCuboidShape(0.0, 13.0, 0.0, 16.0, 15.0, 1.0),
			makeCuboidShape(0.0, 13.0, 15.0, 16.0, 15.0, 16.0),
			makeCuboidShape(0.0, 13.0, 1.0, 1.0, 15.0, 15.0),
			makeCuboidShape(15.0, 13.0, 1.0, 16.0, 15.0, 15.0)
		).reduce { v1: VoxelShape, v2: VoxelShape ->
			VoxelShapes.combineAndSimplify(v1,
				v2,
				IBooleanFunction.OR)
		}.get()

		val SHAPE_S = Stream.of(
			makeCuboidShape(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
			makeCuboidShape(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
			makeCuboidShape(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
			makeCuboidShape(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
			makeCuboidShape(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
			makeCuboidShape(15.0, 13.0, 0.0, 16.0, 15.0, 16.0),
			makeCuboidShape(0.0, 13.0, 0.0, 1.0, 15.0, 16.0),
			makeCuboidShape(1.0, 13.0, 0.0, 15.0, 15.0, 1.0),
			makeCuboidShape(1.0, 13.0, 15.0, 15.0, 15.0, 16.0)
		).reduce { v1: VoxelShape, v2: VoxelShape ->
			VoxelShapes.combineAndSimplify(v1,
				v2,
				IBooleanFunction.OR)
		}.get()

		val SHAPE_W = Stream.of(
			makeCuboidShape(0.0, 0.0, 15.0, 1.0, 3.0, 16.0),
			makeCuboidShape(15.0, 0.0, 15.0, 16.0, 3.0, 16.0),
			makeCuboidShape(0.0, 0.0, 0.0, 1.0, 3.0, 1.0),
			makeCuboidShape(15.0, 0.0, 0.0, 16.0, 3.0, 1.0),
			makeCuboidShape(0.0, 3.0, 0.0, 16.0, 13.0, 16.0),
			makeCuboidShape(0.0, 13.0, 15.0, 16.0, 15.0, 16.0),
			makeCuboidShape(0.0, 13.0, 0.0, 16.0, 15.0, 1.0),
			makeCuboidShape(15.0, 13.0, 1.0, 16.0, 15.0, 15.0),
			makeCuboidShape(0.0, 13.0, 1.0, 1.0, 15.0, 15.0)
		).reduce { v1: VoxelShape, v2: VoxelShape ->
			VoxelShapes.combineAndSimplify(v1,
				v2,
				IBooleanFunction.OR)
		}.get()
	}
}
