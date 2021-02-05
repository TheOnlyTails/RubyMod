package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.blocks.*
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Properties
import net.minecraft.block.PressurePlateBlock.Sensitivity
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorldReader
import net.minecraftforge.common.ToolType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom blocks.
 *
 * @author TheOnlyTails
 */
object BlockRegistry {
	val BLOCKS = KDeferredRegister(ForgeRegistries.BLOCKS, com.theonlytails.rubymod.RubyMod.MOD_ID)

	val RUBY_BLOCK by BLOCKS.registerObject("ruby_block") {
		Block(Properties.create(Material.IRON)
			.hardnessAndResistance(5.0f, 6.0f)
			.sound(SoundType.METAL)
			.harvestTool(ToolType.PICKAXE)
			.harvestLevel(2)
			.setRequiresTool())
	}

	val RUBY_SLAB by BLOCKS.registerObject("ruby_slab") {
		SlabBlock(Properties.from(RUBY_BLOCK))
	}

	val RUBY_PRESSURE_PLATE by BLOCKS.registerObject("ruby_pressure_plate") {
		PressurePlateBlock(Sensitivity.MOBS, Properties.from(RUBY_BLOCK))
	}

	val RUBY_BUTTON by BLOCKS.registerObject("ruby_button") {
		StoneButtonBlock(Properties.from(RUBY_BLOCK))
	}

	val RUBY_STAIRS by BLOCKS.registerObject("ruby_stairs") {
		StairsBlock({ RUBY_BLOCK.defaultState },
			Properties.from(RUBY_BLOCK))
	}

	val RUBY_WALL by BLOCKS.registerObject("ruby_wall") {
		WallBlock(Properties.from(RUBY_BLOCK))
	}

	val RUBY_ORE_BLOCK by BLOCKS.registerObject("ruby_ore") {
		object : OreBlock(Properties.create(Material.ROCK)
			.hardnessAndResistance(3.0f, 3.0f)
			.sound(SoundType.STONE)
			.harvestTool(ToolType.PICKAXE)
			.harvestLevel(2)
			.setRequiresTool()) {
			override fun getExpDrop(
				state: BlockState,
				reader: IWorldReader,
				pos: BlockPos,
				fortune: Int,
				silktouch: Int,
			) = 3
		}
	}

	val CENTRIFUGE_BLOCK by BLOCKS.registerObject("centrifuge", ::CentrifugeBlock)

	val RUBY_WOOL by BLOCKS.registerObject("ruby_wool") {
		Block(Properties
			.create(Material.WOOL, MaterialColor.CRIMSON_HYPHAE)
			.hardnessAndResistance(0.8f)
			.sound(SoundType.CLOTH))
	}

	val RUBY_CARPET by BLOCKS.registerObject("ruby_carpet", ::RubyCarpetBlock)

	val RUBY_BARREL by BLOCKS.registerObject("ruby_barrel", ::RubyBarrelBlock)

	val LOGIC_GATE by BLOCKS.registerObject("logic_gate") {
		LogicGateBlock(Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().sound(SoundType.METAL))
	}
}
