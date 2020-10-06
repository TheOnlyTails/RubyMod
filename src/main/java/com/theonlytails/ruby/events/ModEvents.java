package com.theonlytails.ruby.events;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.ItemsReg;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onAttackRubySheep(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();

        if (player.getHeldItemMainhand().getItem()
                == ItemsReg.POISONED_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (target instanceof RubySheepEntity) {
                    target.addPotionEffect(new EffectInstance(Effects.POISON, 9 * 20, 1));
                    target.setGlowing(true);

                    if (!player.getEntityWorld().isRemote) {
                        String msg = "I don't think that sheep is feeling so well...";
                        player.sendMessage(
                                new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }
}
