package com.github.theonlytails.rubymod.trades

import com.github.theonlytails.rubymod.registries.ItemRegistry
import com.github.theonlytails.rubymod.registries.VillagerProfessionsRegistry
import net.minecraft.item.Items
import net.minecraftforge.event.village.VillagerTradesEvent

object TradesRegisterer {
	fun addVillagerTrades(event: VillagerTradesEvent) {
		if (event.type == VillagerProfessionsRegistry.JEWELER) {
			// Level 1 trades
			event.trades[1].add(ItemsForRubyTrade(Items.COAL, 45, maxUses = 12))
			event.trades[1].add(ItemsForRubyTrade(Items.IRON_NUGGET, 16, maxUses = 9))
			event.trades[1].add(ItemsForRubyTrade(Items.IRON_INGOT, 3, maxUses = 8))
			event.trades[1].add(ItemsForRubyTrade(Items.GOLD_NUGGET, 16, maxUses = 10))

			// Level 2 trades
			event.trades[2].add(ItemsForRubyTrade(Items.GOLD_INGOT, 4, maxUses = 9))
			event.trades[2].add(ItemsForRubyTrade(Items.LAPIS_LAZULI, 25, maxUses = 10))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_PICKAXE,
				sellingItem = ItemRegistry.RUBY_PICKAXE,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_SWORD,
				sellingItem = ItemRegistry.RUBY_SWORD,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_AXE,
				sellingItem = ItemRegistry.RUBY_AXE,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_SHOVEL,
				sellingItem = ItemRegistry.RUBY_SHOVEL,
				maxUses = 1,
				xpValue = 5,
			))
			event.trades[2].add(ItemsForRubyAndItemsTrade(
				Items.IRON_HOE,
				sellingItem = ItemRegistry.RUBY_HOE,
				maxUses = 1,
				xpValue = 5,
			))

			// Level 3 trades
			event.trades[3].add(ItemsForRubyTrade(Items.EMERALD_BLOCK, sellingItemCount = 10, maxUses = 8))

			// Level 4 trades
			event.trades[4].add(ItemsForRubyTrade(Items.DIAMOND, sellingItemCount = 2, maxUses = 2))

			// Level 5 trades
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_HELMET,
				sellingItem = ItemRegistry.RUBY_HELMET,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_CHESTPLATE,
				sellingItem = ItemRegistry.RUBY_CHESTPLATE,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_LEGGINGS,
				sellingItem = ItemRegistry.RUBY_LEGGINGS,
				maxUses = 1,
			))
			event.trades[5].add(ItemsForRubyAndItemsTrade(
				Items.IRON_BOOTS,
				sellingItem = ItemRegistry.RUBY_BOOTS,
				maxUses = 1,
			))
		}
	}

}