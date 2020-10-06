package com.theonlytails.ruby.items;

import com.theonlytails.ruby.TheOnlyTails;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class FuelBlockItem extends BlockItem {
    protected int burnTime;

    public FuelBlockItem(Block blockIn, int burnTime) {
        super(blockIn, TheOnlyTails.RUBY_TAB_PROP);

        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
