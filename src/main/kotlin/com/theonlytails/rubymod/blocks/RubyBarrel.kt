@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.blocks

import com.theonlytails.rubymod.registries.TileEntityTypes
import com.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.inventory.InventoryHelper
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.state.BooleanProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.stats.Stats
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.network.NetworkHooks
import net.minecraftforge.items.ItemHandlerHelper

/**
 * The block class for the ruby barrel.
 *
 * @author TheOnlyTails
 */
class RubyBarrel : Block(Properties.of(Material.METAL, MaterialColor.COLOR_RED)
	.strength(3.5f)
	.sound(SoundType.METAL)
	.harvestTool(ToolType.PICKAXE)
	.harvestLevel(2)
	.requiresCorrectToolForDrops()
) {

	init {
		registerDefaultState(stateDefinition.any().setValue(PROPERTY_OPEN, false))
	}

	override fun use(
		state: BlockState,
		worldIn: World,
		pos: BlockPos,
		player: PlayerEntity,
		handIn: Hand,
		hit: BlockRayTraceResult,
	): ActionResultType {
		if (!worldIn.isClientSide) {
			val tileEntity = worldIn.getBlockEntity(pos) as INamedContainerProvider?
			if (tileEntity is RubyBarrelTileEntity) {
				NetworkHooks.openGui(player as ServerPlayerEntity, tileEntity, pos)
				player.awardStat(Stats.OPEN_BARREL)
			}
		}

		return ActionResultType.SUCCESS
	}

	override fun onRemove(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
		if (state.block != newState.block) {
			val tileEntity = worldIn.getBlockEntity(pos)
			if (tileEntity is RubyBarrelTileEntity) {
				dropItems(tileEntity, worldIn, pos)
				worldIn.updateNeighbourForOutputSignal(pos, this)
			}
		}
		super.onRemove(state, worldIn, pos, newState, isMoving)
	}

	override fun createTileEntity(state: BlockState, world: IBlockReader) = TileEntityTypes.rubyBarrel.create()

	override fun hasTileEntity(state: BlockState) = true

	override fun getAnalogOutputSignal(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
		val rubyBarrel = worldIn.getBlockEntity(pos)
		return if (rubyBarrel is RubyBarrelTileEntity) ItemHandlerHelper.calcRedstoneFromInventory(rubyBarrel.itemHandler) else 0
	}

	override fun hasAnalogOutputSignal(state: BlockState) = true

	override fun createBlockStateDefinition(builder: StateContainer.Builder<Block, BlockState>) {
		builder.add(PROPERTY_OPEN)
	}

	companion object {
		val PROPERTY_OPEN: BooleanProperty = BlockStateProperties.OPEN

		private fun dropItems(rubyBarrel: RubyBarrelTileEntity, world: World, pos: BlockPos) {
			(0..rubyBarrel.itemHandler.slots)
				.map { slot -> rubyBarrel.itemHandler.getStackInSlot(slot) }
				.filter { !it.isEmpty }
				.forEach {
					InventoryHelper.dropItemStack(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), it)
				}
		}
	}
}
