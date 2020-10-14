package com.github.theonlytails.rubymod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.NotNull;

public class RubyOreBlock extends OreBlock {
    public RubyOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .setRequiresTool());
    }

    @Override
    public int getExpDrop(@NotNull BlockState state, @NotNull IWorldReader reader, @NotNull BlockPos pos, int fortune, int silktouch) {
        return 3;
    }
}
