package com.github.theonlytails.rubymod.blocks

import com.github.theonlytails.rubymod.registries.TileEntityTypes
import com.github.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.inventory.InventoryHelper
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.state.BooleanProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.stats.Stats
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.common.ToolType
import net.minecraftforge.fml.network.NetworkHooks
import net.minecraftforge.items.ItemHandlerHelper
import java.util.stream.IntStream

class RubyBarrelBlock : Block(Properties.create(
	Material.IRON, MaterialColor.RED)
	.hardnessAndResistance(3.5f)
	.sound(SoundType.METAL)
	.harvestTool(ToolType.PICKAXE)
	.harvestLevel(2)
	.setRequiresTool()) {

	init {
		defaultState = getStateContainer().baseState.with(PROPERTY_OPEN, false)
	}

	override fun onBlockActivated(
		state: BlockState,
		worldIn: World,
		pos: BlockPos,
		player: PlayerEntity,
		handIn: Hand,
		hit: BlockRayTraceResult,
	): ActionResultType {
		if (!worldIn.isRemote()) {
			val tileEntity = worldIn.getTileEntity(pos) as INamedContainerProvider?
			if (tileEntity is RubyBarrelTileEntity) {
				NetworkHooks.openGui(player as ServerPlayerEntity, tileEntity, pos)
				player.addStat(Stats.OPEN_BARREL)
			}
		}
		return ActionResultType.SUCCESS
	}

	override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
		if (state.block !== newState.block) {
			val tileEntity = worldIn.getTileEntity(pos)
			if (tileEntity is RubyBarrelTileEntity) {
				dropItems(tileEntity, worldIn, pos)
				worldIn.updateComparatorOutputLevel(pos, this)
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving)
	}

	override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity? {
		return TileEntityTypes.RUBY_BARREL.create()
	}

	override fun hasTileEntity(state: BlockState) = true

	override fun getComparatorInputOverride(blockState: BlockState, worldIn: World, pos: BlockPos): Int {
		val rubyBarrel = worldIn.getTileEntity(pos)
		return if (rubyBarrel is RubyBarrelTileEntity) ItemHandlerHelper.calcRedstoneFromInventory(rubyBarrel.itemHandler) else 0
	}

	override fun hasComparatorInputOverride(state: BlockState) = true

	override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
		builder.add(PROPERTY_OPEN)
	}

	companion object {
		val PROPERTY_OPEN: BooleanProperty = BlockStateProperties.OPEN

		private fun dropItems(rubyBarrel: RubyBarrelTileEntity, world: World, pos: BlockPos) {
			IntStream.range(0, rubyBarrel.itemHandler.slots)
				.mapToObj { slot: Int -> rubyBarrel.itemHandler.getStackInSlot(slot) }
				.filter { stack: ItemStack -> !stack.isEmpty }
				.forEach { stack: ItemStack ->
					InventoryHelper.spawnItemStack(world,
						pos.x.toDouble(),
						pos.y.toDouble(),
						pos.z.toDouble(),
						stack)
				}
		}
	}
}