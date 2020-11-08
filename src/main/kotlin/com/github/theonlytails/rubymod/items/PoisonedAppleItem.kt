package com.github.theonlytails.rubymod.items

import com.github.theonlytails.rubymod.entities.RubySheepEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.text.StringTextComponent

/**
 * The poisoned apple class.
 *
 * @author TheOnlyTails
 */
class PoisonedAppleItem : Item(Properties()
	.group(ItemGroup.FOOD)
	.food(Food.Builder()
		.hunger(7)
		.saturation(1.2f)
		// Gives you Nausea 2 for 7 seconds 100% of the time;
		.effect({ EffectInstance(Effects.NAUSEA, 7 * 20, 1) }, 1f)
		// Gives you Poison 2 for 9 seconds 100% of the time;
		.effect({ EffectInstance(Effects.POISON, 9 * 20, 1) }, 1f)
		// Gives you Glowing 1 for 10 seconds 100% of the time;
		.effect({ EffectInstance(Effects.GLOWING, 10 * 20, 0) }, 1f)
		// Gives you Hunger 3 for 3 seconds 10% of the time;
		.effect({ EffectInstance(Effects.HUNGER, 3 * 20, 2) }, 0.1f)
		// Gives you Blindness (!) 3 for 5 seconds 5% of the time;
		.effect({ EffectInstance(Effects.BLINDNESS, 5 * 20, 2) }, 0.05f)
		// Gives you Luck (!) 1 for 1 seconds 50% of the time;
		.effect({ EffectInstance(Effects.LUCK, 20, 0) }, 0.5f)
		.setAlwaysEdible()
		.build())) {

	override fun itemInteractionForEntity(
		stack: ItemStack,
		player: PlayerEntity,
		target: LivingEntity,
		hand: Hand,
	): ActionResultType {
		if (target is SheepEntity) {
			if (target.isAlive && target is RubySheepEntity) {
				target.addPotionEffect(EffectInstance(Effects.POISON, 9 * 20, 1))
				target.setGlowing(true)

				if (!player.world.isRemote) {
					player.sendMessage(StringTextComponent("I don't think that sheep is feeling so well..."),
						player.uniqueID)

					stack.shrink(1)
				}
			}

			// func_233537_a_ -> resultSuccessClient()
			return ActionResultType.func_233537_a_(player.world.isRemote)
		}
		return ActionResultType.PASS
	}
}

