package com.theonlytails.ruby.items;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.util.enums.RubyItemTier;
import net.minecraft.item.HoeItem;

public class RubyHoe extends HoeItem {

    public RubyHoe() {
        super(RubyItemTier.RUBY, -2, -0.5f, TheOnlyTails.RUBY_TAB_PROP);
    }
}
