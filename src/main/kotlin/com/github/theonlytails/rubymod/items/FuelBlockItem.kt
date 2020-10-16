package com.github.theonlytails.rubymod.items

import com.github.theonlytails.rubymod.RubyMod
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack

class FuelBlockItem(blockIn: Block, private val burnTime: Int) :
	BlockItem(blockIn, RubyMod.RUBY_TAB_PROPERTY) {

	override fun getBurnTime(itemStack: ItemStack): Int {
		return burnTime
	}
}