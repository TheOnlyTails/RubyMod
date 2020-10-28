package com.github.theonlytails.rubymod.blocks

import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.IWorldReader

/**
 * The block class for the ruby carpet.
 *
 * @author TheOnlyTails
 */
class RubyCarpetBlock : Block(Properties
	.create(Material.CARPET, MaterialColor.CRIMSON_HYPHAE)
	.hardnessAndResistance(0.1f)
	.sound(SoundType.CLOTH)) {

	override fun getShape(
		state: BlockState,
		worldIn: IBlockReader,
		pos: BlockPos,
		context: ISelectionContext,
	): VoxelShape = SHAPE

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 */
	override fun updatePostPlacement(
		stateIn: BlockState,
		facing: Direction,
		facingState: BlockState,
		worldIn: IWorld,
		currentPos: BlockPos,
		facingPos: BlockPos,
	): BlockState {
		return if (!stateIn.isValidPosition(worldIn,
				currentPos)
		) Blocks.AIR.defaultState else super.updatePostPlacement(stateIn,
			facing,
			facingState,
			worldIn,
			currentPos,
			facingPos)
	}

	override fun isValidPosition(state: BlockState, worldIn: IWorldReader, pos: BlockPos) =
		!worldIn.isAirBlock(pos.down())

	companion object {
		val SHAPE: VoxelShape = makeCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
	}
}