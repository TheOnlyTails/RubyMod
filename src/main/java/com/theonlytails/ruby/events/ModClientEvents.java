package com.theonlytails.ruby.events;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.init.ItemsRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onDamagePig(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();

        if (player.getHeldItemMainhand().getItem()
                == ItemsRegistry.POISONED_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (target instanceof PigEntity) {
                    target.addPotionEffect(new EffectInstance(Effects.POISON, 9 * 20, 1));
                    target.setGlowing(true);

                    if (!player.getEntityWorld().isRemote) {
                        String msg = "I don't think that pig is feeling so well...";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }
}
