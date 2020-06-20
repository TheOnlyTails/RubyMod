package com.example.tutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RubyOreBlock extends Block {

    public RubyOreBlock() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 3.0f)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }
}
