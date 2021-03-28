package com.theonlytails.rubymod.items

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
import java.util.function.Supplier

/**
 * A custom spawn egg.
 *
 * @author TheOnlyTails
 */
class CustomSpawnEggItem(
	entityTypeSupplier: Supplier<out EntityType<*>>,
	primaryColorIn: Int,
	secondaryColorIn: Int,
	builder: Properties = rubyTabProperty,
) : SpawnEggItem(@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") null,
	primaryColorIn,
	secondaryColorIn,
	builder) {
	private val entityTypeSupplier = Lazy.of(entityTypeSupplier::get)

	override fun getType(nbt: CompoundNBT?): EntityType<*> = entityTypeSupplier.get()

	companion object {
		private val unaddedEggs: MutableList<CustomSpawnEggItem> = ArrayList()

		fun initSpawnEggs() {
			val dispenserBehavior: DefaultDispenseItemBehavior = object : DefaultDispenseItemBehavior() {
				override fun execute(source: IBlockSource, stack: ItemStack): ItemStack {
					val direction = source.blockState.getValue(DispenserBlock.FACING)

					(stack.item as SpawnEggItem).getType(stack.tag).apply {
						spawn(
							source.level, stack, null, source.pos.relative(direction),
							SpawnReason.DISPENSER, direction != Direction.UP, false
						)
					}

					stack.shrink(1)
					return stack
				}
			}

			unaddedEggs.forEach {
				BY_ID[it.getType(null)] = it
				DispenserBlock.registerBehavior(it, dispenserBehavior)
			}

			unaddedEggs.clear()
		}
	}

	init {
		unaddedEggs.add(this)
	}
}
