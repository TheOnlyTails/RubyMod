package com.theonlytails.ruby.items;

import com.theonlytails.ruby.TheOnlyTails;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(TheOnlyTails.RUBY));
    }
}