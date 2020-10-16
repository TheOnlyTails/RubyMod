package com.github.theonlytails.rubymod.items

import net.minecraft.block.DispenserBlock
import net.minecraft.dispenser.DefaultDispenseItemBehavior
import net.minecraft.dispenser.IBlockSource
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.item.ItemStack
import net.minecraft.item.SpawnEggItem
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.util.Lazy
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import java.util.ArrayList
import java.util.Objects
import java.util.function.Supplier

class CustomSpawnEggItem(
	entityTypeSupplier: Supplier<out EntityType<*>>,
	primaryColorIn: Int,
	secondaryColorIn: Int,
	builder: Properties,
) :
	SpawnEggItem(null, primaryColorIn, secondaryColorIn, builder) {
	private val entityTypeSupplier: Lazy<out EntityType<*>> = Lazy.of(entityTypeSupplier::get)
	override fun getType(nbt: CompoundNBT?): EntityType<*> {
		return entityTypeSupplier.get()
	}

	companion object {
		private val UNADDED_EGGS: MutableList<CustomSpawnEggItem> = ArrayList()
		fun initSpawnEggs() {
			val eggs: MutableMap<EntityType<*>, CustomSpawnEggItem>? = Objects.requireNonNull(
				ObfuscationReflectionHelper.getPrivateValue<MutableMap<EntityType<*>, CustomSpawnEggItem>, SpawnEggItem>(
					SpawnEggItem::class.java, null, "field_195987_b"))

			val dispenserBehavior: DefaultDispenseItemBehavior = object : DefaultDispenseItemBehavior() {
				override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
					val direction = source.blockState.get(DispenserBlock.FACING)
					val type = (stack.item as SpawnEggItem).getType(stack.tag)
					type.spawn(source.world, stack, null, source.blockPos.offset(direction),
						SpawnReason.DISPENSER, direction != Direction.UP, false)
					stack.shrink(1)
					return stack
				}
			}
			for (spawnEgg in UNADDED_EGGS) {
				eggs?.set(spawnEgg.getType(null), spawnEgg)
				DispenserBlock.registerDispenseBehavior(spawnEgg, dispenserBehavior)
			}
			UNADDED_EGGS.clear()
		}
	}

	init {
		UNADDED_EGGS.add(this)
	}
}