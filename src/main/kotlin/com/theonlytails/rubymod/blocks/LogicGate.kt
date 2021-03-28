package com.theonlytails.rubymod.blocks

import com.theonlytails.rubymod.util.enums.LogicGateModes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.RedstoneDiodeBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.EnumProperty
import net.minecraft.state.StateContainer
import net.minecraft.util.ActionResultType
import net.minecraft.util.Direction
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.IWorld
import net.minecraft.world.World

class LogicGate : RedstoneDiodeBlock(Properties.of(Material.DECORATION).instabreak().sound(SoundType.METAL)) {
    init {
        this.registerDefaultState(
            this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(MODE, LogicGateModes.OR)
        )
    }

    override fun getInputSignal(world: World, pos: BlockPos, state: BlockState): Int {
        val facing = state.getValue(FACING)

        val firstInput = world.getSignal(pos.relative(facing.clockWise), facing.clockWise) > 0
        val secondInput = world.getSignal(pos.relative(facing.counterClockWise), facing.counterClockWise) > 0

        return state.getValue(MODE)(firstInput, secondInput)
    }

    override fun use(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        result: BlockRayTraceResult,
    ): ActionResultType = if (player.abilities.mayBuild) {
        world.setBlock(pos, state.cycle(MODE), 3)
        ActionResultType.sidedSuccess(world.isClientSide)
    } else ActionResultType.PASS

    override fun getDelay(state: BlockState) = 1

    override fun createBlockStateDefinition(builder: StateContainer.Builder<Block, BlockState>) {
        builder.add(FACING, POWERED, MODE)
    }

    override fun canConnectRedstone(
	    state: BlockState,
	    world: IBlockReader,
	    pos: BlockPos,
	    side: Direction?,
    ) = side == state.getValue(FACING)
            || side == state.getValue(FACING).clockWise
            || side == state.getValue(FACING).counterClockWise

    override fun isAlternateInput(state: BlockState) = isDiode(state)

    @Suppress("DEPRECATION")
    override fun updateShape(
        stateIn: BlockState,
        facing: Direction,
        facingState: BlockState,
        worldIn: IWorld,
        currentPos: BlockPos,
        facingPos: BlockPos,
    ): BlockState =
        if (!worldIn.isClientSide && facing.axis != stateIn.getValue(FACING).axis)
            stateIn.setValue(MODE, stateIn.getValue(MODE))
        else super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)

    companion object {
        val MODE: EnumProperty<LogicGateModes> = EnumProperty.create(
            "logic_gate_mode",
            LogicGateModes::class.java,
            LogicGateModes.AND,
            LogicGateModes.OR,
        )
    }
}
