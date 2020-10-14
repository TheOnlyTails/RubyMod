package com.github.theonlytails.ruby.items;

import com.github.theonlytails.ruby.RubyMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class FuelBlockItem extends BlockItem {
    private final int burnTime;

    public FuelBlockItem(Block blockIn, int burnTime) {
        super(blockIn, RubyMod.RUBY_TAB_PROPERTY);

        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
