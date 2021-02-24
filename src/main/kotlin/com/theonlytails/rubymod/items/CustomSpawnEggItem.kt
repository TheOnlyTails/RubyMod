package com.theonlytails.rubymod.items

import com.google.common.collect.Maps
import com.theonlytails.rubymod.rubyTabProperty
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
import java.util.function.Supplier

/**
 * A custom spawn egg.
 *
 * @author TheOnlyTails
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CustomSpawnEggItem(
	entityTypeSupplier: Supplier<out EntityType<*>>,
	primaryColorIn: Int,
	secondaryColorIn: Int,
	builder: Properties = rubyTabProperty,
) : SpawnEggItem(null, primaryColorIn, secondaryColorIn, builder) {
	private val entityTypeSupplier: Lazy<out EntityType<*>> = Lazy.of(entityTypeSupplier::get)

	override fun getType(nbt: CompoundNBT?): EntityType<*> = entityTypeSupplier.get()

	companion object {
		private val unaddedEggs: MutableList<CustomSpawnEggItem> = ArrayList()

		fun initSpawnEggs() {
			val eggs =
				ObfuscationReflectionHelper.getPrivateValue<MutableMap<EntityType<*>, CustomSpawnEggItem>, SpawnEggItem>(
					SpawnEggItem::class.java, null, "field_195987_b") ?: Maps.newIdentityHashMap()

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

			unaddedEggs.forEach {
				eggs[it.getType(null)] = it
				DispenserBlock.registerDispenseBehavior(it, dispenserBehavior)
			}

			unaddedEggs.clear()
		}
	}

	init {
		unaddedEggs.add(this)
	}
}
