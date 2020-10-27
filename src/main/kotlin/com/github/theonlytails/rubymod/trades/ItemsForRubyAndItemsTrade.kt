package com.github.theonlytails.rubymod.trades

import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.merchant.villager.VillagerTrades
import net.minecraft.item.Item
import net.minecraft.item.MerchantOffer
import java.util.Random
import net.minecraft.item.ItemStack as IS

class ItemsForRubyAndItemsTrade(
	private val buyingItem: Item,
	private val buyingItemCount: Int = 1,
	private val rubyCount: Int = 1,
	private val sellingItem: Item,
	private val sellingItemCount: Int = 1,
	private val maxUses: Int,
	private val xpValue: Int = 2,
) : VillagerTrades.ITrade {
	private val priceMultiplier = 0.05f

	override fun getOffer(p0: Entity, p1: Random) =
		MerchantOffer(
			IS(ItemRegistry.RUBY, rubyCount),
			IS(buyingItem, buyingItemCount),
			IS(sellingItem, sellingItemCount),
			maxUses,
			xpValue,
			priceMultiplier
		)
}