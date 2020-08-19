package com.theonlytails.ruby.items;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.entities.RubySheepEntity;
import com.theonlytails.ruby.init.RubyEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

public class Ruby extends Item {
    public Ruby() {
        super(new Item.Properties().group(TheOnlyTails.RUBY));
    }

    @Override
    public @NotNull ActionResultType itemInteractionForEntity(@NotNull ItemStack stack,
                                                              @NotNull PlayerEntity playerIn,
                                                              @NotNull LivingEntity target,
                                                              @NotNull Hand hand) {
        if (target instanceof SheepEntity) {
            SheepEntity sheepentity = (SheepEntity)target;
            if (sheepentity.isAlive() && !sheepentity.getSheared() && !(sheepentity instanceof RubySheepEntity)) {
                if (!playerIn.world.isRemote) {
                    RubySheepEntity rubySheepEntity = RubyEntityTypes.RUBY_SHEEP.get().create(playerIn.world);
                    if (rubySheepEntity != null) {
                        rubySheepEntity.setLocationAndAngles(sheepentity.getPosX(), sheepentity.getPosY(), sheepentity.getPosZ(), sheepentity.rotationYaw, sheepentity.rotationPitch);
                        rubySheepEntity.onInitialSpawn(playerIn.world, playerIn.world.getDifficultyForLocation(rubySheepEntity.getPosition()), SpawnReason.CONVERSION, null, null);
                        playerIn.world.addEntity(rubySheepEntity);
                        sheepentity.remove();
                    }

                }

                return ActionResultType.func_233537_a_(playerIn.world.isRemote);
            }
        }

        return ActionResultType.PASS;
    }
}
