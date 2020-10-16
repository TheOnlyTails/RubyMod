package com.github.theonlytails.rubymod.blocks

import net.minecraft.block.BlockState
import net.minecraft.block.OreBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorldReader
import net.minecraftforge.common.ToolType

class RubyOreBlock : OreBlock(Properties.create(Material.ROCK)
	.hardnessAndResistance(3.0f, 3.0f)
	.sound(SoundType.STONE)
	.harvestTool(ToolType.PICKAXE)
	.harvestLevel(2)
	.setRequiresTool()) {

	override fun getExpDrop(state: BlockState, reader: IWorldReader, pos: BlockPos, fortune: Int, silktouch: Int): Int {
		return 3
	}
}