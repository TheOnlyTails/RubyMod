package com.theonlytails.ruby.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class RubyWool extends Block {
    public RubyWool() {
        super(AbstractBlock.Properties
                .create(
                        Material.WOOL,
                        MaterialColor.CRIMSON_HYPHAE)
                .hardnessAndResistance(0.8f)
                .sound(SoundType.CLOTH));
    }
}
