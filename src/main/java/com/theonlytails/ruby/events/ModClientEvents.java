package com.theonlytails.ruby.events;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.util.RegistryHandler;
import com.theonlytails.ruby.util.RegistryHandlerBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOnlyTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onDamagePig(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();

        if (player.getHeldItemMainhand().getItem()
                == RegistryHandler.POISONED_APPLE.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();
                if (target instanceof PigEntity) {
                    target.addPotionEffect(new EffectInstance(Effects.POISON, 9 * 20, 1));
                    target.setGlowing(true);

                    if (!player.getEntityWorld().isRemote) {
                        String msg = "I think that pig isn't feeling so well...";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }
}
