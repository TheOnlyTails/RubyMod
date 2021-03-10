package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.blocks.Centrifuge
import com.theonlytails.rubymod.blocks.LogicGate
import com.theonlytails.rubymod.blocks.RubyBarrel
import com.theonlytails.rubymod.blocks.RubyCarpet
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
		Block(
			Properties.of(Material.METAL)
				.strength(5.0f, 6.0f)
				.sound(SoundType.METAL)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(2)
				.requiresCorrectToolForDrops()
		)
	}

	val rubySlab by blocks.registerObject("ruby_slab") {
		SlabBlock(Properties.copy(rubyBlock))
	}

	val rubyPressurePlate by blocks.registerObject("ruby_pressure_plate") {
		PressurePlateBlock(Sensitivity.MOBS, Properties.copy(rubyBlock))
	}

	val rubyButton by blocks.registerObject("ruby_button") {
		StoneButtonBlock(Properties.copy(rubyBlock))
	}

	val rubyStairs by blocks.registerObject("ruby_stairs") {
		StairsBlock({ rubyBlock.defaultBlockState() }, Properties.copy(rubyBlock))
	}

	val rubyWall by blocks.registerObject("ruby_wall") {
		WallBlock(Properties.copy(rubyBlock))
	}

	val rubyOre by blocks.registerObject("ruby_ore") {
		object : OreBlock(
			Properties.of(Material.STONE)
				.strength(3.0f, 3.0f)
				.sound(SoundType.STONE)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(2)
				.requiresCorrectToolForDrops()
		) {
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
		Block(
			Properties.of(Material.WOOL, MaterialColor.CRIMSON_HYPHAE)
				.strength(0.8f)
				.sound(SoundType.WOOL)
		)
	}

	val rubyCarpet by blocks.registerObject("ruby_carpet", ::RubyCarpet)

	val rubyBarrel by blocks.registerObject("ruby_barrel", ::RubyBarrel)

	val logicGate by blocks.registerObject("logic_gate", ::LogicGate)
}
