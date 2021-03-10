@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SoundType
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
class RubyCarpet : Block(
    Properties.of(Material.CLOTH_DECORATION, MaterialColor.CRIMSON_HYPHAE)
        .strength(0.1f)
        .sound(SoundType.WOOL)
) {

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
    override fun updateShape(
        stateIn: BlockState,
        facing: Direction,
        facingState: BlockState,
        worldIn: IWorld,
        currentPos: BlockPos,
        facingPos: BlockPos,
    ): BlockState =
        if (!stateIn.canSurvive(worldIn, currentPos)) Blocks.AIR.defaultBlockState()
        else super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)

    override fun canSurvive(state: BlockState, worldIn: IWorldReader, pos: BlockPos) =
        !worldIn.isEmptyBlock(pos.below())

    companion object {
        val SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
    }
}
