package com.theonlytails.rubymod.items

import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.world.IServerWorld

/**
 * Holds the custom functionality of rubies.
 *
 * @author TheOnlyTails
 */
class RubyItem : Item(com.theonlytails.rubymod.RubyMod.RUBY_TAB_PROPERTY) {
	override fun itemInteractionForEntity(
		stack: ItemStack,
		playerIn: PlayerEntity,
		target: LivingEntity,
		hand: Hand,
	): ActionResultType {

		if (target is SheepEntity) {
			if (target.isAlive && !target.sheared && target !is RubySheepEntity) {
				if (!playerIn.world.isRemote) {
					val rubySheepEntity = EntityTypeRegistry.RUBY_SHEEP.create(playerIn.world)
					if (rubySheepEntity != null) {
						rubySheepEntity.setLocationAndAngles(
							target.posX,
							target.posY,
							target.posZ,
							target.rotationYaw,
							target.rotationPitch
						)
						rubySheepEntity.onInitialSpawn(
							playerIn.world as IServerWorld,
							playerIn.world.getDifficultyForLocation(rubySheepEntity.position),
							SpawnReason.CONVERSION,
							null,
							null
						)
						playerIn.world.addEntity(rubySheepEntity)
						target.remove()

						stack.shrink(1)
					}
				}

				// func_233537_a_ -> resultSuccessClient()
				return ActionResultType.func_233537_a_(playerIn.world.isRemote)
			}
		}
		return ActionResultType.PASS
	}
}
