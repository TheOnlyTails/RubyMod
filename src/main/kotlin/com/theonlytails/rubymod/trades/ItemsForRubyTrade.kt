package com.theonlytails.rubymod.trades

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.merchant.villager.VillagerTrades
import net.minecraft.item.Item
import net.minecraft.item.MerchantOffer
import java.util.Random
import net.minecraft.item.ItemStack as IS

/**
 * A trade that takes rubies, and returns items.
 *
 * @author TheOnlyTails
 *
 * @property rubyCount The number of rubies taken.
 * @property sellingItem The item being returned.
 * @property sellingItemCount The number of items being returned.
 * @property maxUses The number of times this trade can be used before restocking is required.
 * @property xpValue The number of XP the villager receives when the trade is done.
 */
class ItemsForRubyTrade(
	private val sellingItem: Item,
	private val sellingItemCount: Int = 1,
	private val rubyCount: Int = 1,
	private val maxUses: Int,
	private val xpValue: Int = 2,
) : VillagerTrades.ITrade {
	private val priceMultiplier = 0.05f

	override fun getOffer(p0: Entity, p1: Random) =
		MerchantOffer(
			IS(ItemRegistry.RUBY, rubyCount),
			IS(sellingItem, sellingItemCount),
			maxUses,
			xpValue,
			priceMultiplier
		)
}