package com.theonlytails.rubymod.items

import com.github.theonlytails.rubymod.entities.RubySheepEntity
import com.github.theonlytails.rubymod.util.effect
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
		.effect(1f) { EffectInstance(Effects.NAUSEA, 7 * 20, 1) }
		// Gives you Poison 2 for 9 seconds 100% of the time;
		.effect(1f) { EffectInstance(Effects.POISON, 9 * 20, 1) }
		// Gives you Glowing 1 for 10 seconds 100% of the time;
		.effect(1f) { EffectInstance(Effects.GLOWING, 10 * 20, 0) }
		// Gives you Hunger 3 for 3 seconds 10% of the time;
		.effect(0.1f) { EffectInstance(Effects.HUNGER, 3 * 20, 2) }
		// Gives you Blindness (!) 3 for 5 seconds 5% of the time;
		.effect(0.05f) { EffectInstance(Effects.BLINDNESS, 5 * 20, 2) }
		// Gives you Luck (!) 1 for 1 seconds 50% of the time;
		.effect(0.5f) { EffectInstance(Effects.LUCK, 20, 0) }
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