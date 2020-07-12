package com.example.tutorial.items;

import com.example.tutorial.TheOnlyTails;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(TheOnlyTails.RUBY));
    }
}