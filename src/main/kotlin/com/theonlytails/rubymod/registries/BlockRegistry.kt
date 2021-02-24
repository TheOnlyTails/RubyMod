package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
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
	val blocks = KDeferredRegister(ForgeRegistries.BLOCKS, MOD_ID)

	val rubyBlock by blocks.registerObject("ruby_block") {
		Block(Properties.create(Material.IRON)
			.hardnessAndResistance(5.0f, 6.0f)
			.sound(SoundType.METAL)
			.harvestTool(ToolType.PICKAXE)
			.harvestLevel(2)
			.setRequiresTool())
	}

	val rubySlab by blocks.registerObject("ruby_slab") {
		SlabBlock(Properties.from(rubyBlock))
	}

	val rubyPressurePlate by blocks.registerObject("ruby_pressure_plate") {
		PressurePlateBlock(Sensitivity.MOBS, Properties.from(rubyBlock))
	}

	val rubyButton by blocks.registerObject("ruby_button") {
		StoneButtonBlock(Properties.from(rubyBlock))
	}

	val rubyStairs by blocks.registerObject("ruby_stairs") {
		StairsBlock({ rubyBlock.defaultState }, Properties.from(rubyBlock))
	}

	val rubyWall by blocks.registerObject("ruby_wall") {
		WallBlock(Properties.from(rubyBlock))
	}

	val rubyOre by blocks.registerObject("ruby_ore") {
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

	val centrifuge by blocks.registerObject("centrifuge", ::Centrifuge)

	val rubyWool by blocks.registerObject("ruby_wool") {
		Block(Properties
			.create(Material.WOOL, MaterialColor.CRIMSON_HYPHAE)
			.hardnessAndResistance(0.8f)
			.sound(SoundType.CLOTH))
	}

	val rubyCarpet by blocks.registerObject("ruby_carpet", ::RubyCarpet)

	val rubyBarrel by blocks.registerObject("ruby_barrel", ::RubyBarrel)

	val logicGate by blocks.registerObject("logic_gate", ::LogicGate)
}
