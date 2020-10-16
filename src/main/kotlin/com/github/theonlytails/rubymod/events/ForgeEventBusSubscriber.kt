package com.github.theonlytails.rubymod.events

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.entities.RubySheepEntity
import com.github.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.entity.LivingEntity
import net.minecraft.potion.EffectInstance
import net.minecraft.potion.Effects
import net.minecraft.util.text.StringTextComponent
import net.minecraftforge.event.entity.player.AttackEntityEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
object ForgeEventBusSubscriber {

	@SubscribeEvent
	fun onAttackRubySheep(event: AttackEntityEvent) {
		val player = event.player

		if (player.heldItemMainhand.item === ItemRegistry.POISONED_APPLE) {
			if (event.target.isAlive) {

				val target = event.target as LivingEntity
				if (target is RubySheepEntity) {
					target.addPotionEffect(EffectInstance(Effects.POISON, 9 * 20, 1))
					target.setGlowing(true)

					if (!player.entityWorld.isRemote) {
						player.sendMessage(StringTextComponent("I don't think that sheep is feeling so well..."),
							player.uniqueID)
					}
				}
			}
		}
	}
}