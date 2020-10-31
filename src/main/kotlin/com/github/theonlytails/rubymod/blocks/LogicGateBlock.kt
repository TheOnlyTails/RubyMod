package com.github.theonlytails.rubymod.blocks

import com.github.theonlytails.rubymod.util.enums.LogicGateModes
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.RedstoneDiodeBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.EnumProperty
import net.minecraft.state.StateContainer
import net.minecraft.util.ActionResultType
import net.minecraft.util.Direction
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

class LogicGateBlock(properties: Properties) : RedstoneDiodeBlock(properties) {
	init {
		this.defaultState = this.stateContainer.baseState
			.with(HORIZONTAL_FACING, Direction.NORTH)
			.with(POWERED, false)
			.with(MODE, LogicGateModes.OR)
	}

	override fun calculateInputStrength(world: World, pos: BlockPos, state: BlockState): Int {
		val facing = state.get(HORIZONTAL_FACING)

		val firstInput = getPowerOnSide(world, pos.offset(facing.rotateY()), facing.rotateY()) > 0
		val secondInput = getPowerOnSide(world, pos.offset(facing.rotateYCCW()), facing.rotateYCCW()) > 0

		return if (state.get(MODE) == LogicGateModes.OR) {
			if (firstInput && secondInput) 15 else 0
		} else {
			if (firstInput || secondInput) 15 else 0
		}
	}

	override fun onBlockActivated(
		state: BlockState,
		world: World,
		pos: BlockPos,
		player: PlayerEntity,
		hand: Hand,
		result: BlockRayTraceResult,
	): ActionResultType {
		return if (player.abilities.allowEdit) {
			world.setBlockState(pos, state.func_235896_a_(MODE), 3)
			ActionResultType.func_233537_a_(world.isRemote)
		} else {
			ActionResultType.PASS
		}
	}

	override fun getDelay(state: BlockState) = 1

	override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
		builder.add(HORIZONTAL_FACING, POWERED, MODE)
	}

	override fun canConnectRedstone(
		state: BlockState,
		world: IBlockReader?,
		pos: BlockPos?,
		side: Direction?,
	) = side == state[HORIZONTAL_FACING] || side == state.get(HORIZONTAL_FACING)
		.rotateY() || side == state.get(HORIZONTAL_FACING).rotateYCCW()

	override fun getStateForPlacement(context: BlockItemUseContext): BlockState = defaultState

	override fun isAlternateInput(state: BlockState) = isDiode(state)

	companion object {
		val MODE: EnumProperty<LogicGateModes> = EnumProperty
			.create(
				"logic_gate_mode",
				LogicGateModes::class.java,
				LogicGateModes.AND,
				LogicGateModes.OR,
			)
	}
}