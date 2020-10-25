package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.blocks.CentrifugeBlock
import com.github.theonlytails.rubymod.blocks.RubyBarrelBlock
import com.github.theonlytails.rubymod.blocks.RubyCarpetBlock
import net.minecraft.block.*
import net.minecraft.block.PressurePlateBlock.Sensitivity
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorldReader
import net.minecraftforge.common.ToolType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object BlockRegistry {
	val BLOCKS = KDeferredRegister(ForgeRegistries.BLOCKS, RubyMod.MOD_ID)

	val RUBY_BLOCK by BLOCKS.register("ruby_block") {
		Block(AbstractBlock.Properties.create(Material.IRON)
			.hardnessAndResistance(5.0f, 6.0f)
			.sound(SoundType.METAL)
			.harvestTool(ToolType.PICKAXE)
			.harvestLevel(2)
			.setRequiresTool())
	}

	val RUBY_SLAB by BLOCKS.register("ruby_slab") {
		SlabBlock(AbstractBlock.Properties.from(RUBY_BLOCK))
	}

	val RUBY_PRESSURE_PLATE by BLOCKS.register("ruby_pressure_plate") {
		PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(RUBY_BLOCK))
	}

	val RUBY_BUTTON by BLOCKS.register("ruby_button") {
		StoneButtonBlock(AbstractBlock.Properties.from(RUBY_BLOCK))
	}

	val RUBY_STAIRS by BLOCKS.register("ruby_stairs") {
		StairsBlock({ RUBY_BLOCK.defaultState },
			AbstractBlock.Properties.from(RUBY_BLOCK))
	}

	val RUBY_ORE_BLOCK by BLOCKS.register("ruby_ore") {
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

	val CENTRIFUGE_BLOCK by BLOCKS.register("centrifuge", ::CentrifugeBlock)

	val RUBY_WOOL by BLOCKS.register("ruby_wool") {
		Block(AbstractBlock.Properties
			.create(Material.WOOL, MaterialColor.CRIMSON_HYPHAE)
			.hardnessAndResistance(0.8f)
			.sound(SoundType.CLOTH))
	}

	val RUBY_CARPET by BLOCKS.register("ruby_carpet", ::RubyCarpetBlock)

	val RUBY_BARREL by BLOCKS.register("ruby_barrel", ::RubyBarrelBlock)
}