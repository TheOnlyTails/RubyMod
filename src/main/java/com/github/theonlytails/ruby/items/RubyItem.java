package com.github.theonlytails.ruby.items;

import com.github.theonlytails.ruby.RubyMod;
import com.github.theonlytails.ruby.entities.RubySheepEntity;
import com.github.theonlytails.ruby.registries.EntityTypeRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

public class RubyItem extends Item {
    public RubyItem() {
        super(RubyMod.RUBY_TAB_PROPERTY);
    }

    @Override
    public @NotNull ActionResultType itemInteractionForEntity(@NotNull ItemStack stack, @NotNull PlayerEntity playerIn, @NotNull LivingEntity target, @NotNull Hand hand) {
        if (target instanceof SheepEntity) {
            SheepEntity sheepEntity = (SheepEntity) target;
            if (sheepEntity.isAlive() && !sheepEntity.getSheared() && !(sheepEntity instanceof RubySheepEntity)) {
                if (!playerIn.world.isRemote) {
                    RubySheepEntity rubySheepEntity = EntityTypeRegistry.RUBY_SHEEP.get().create(playerIn.world);

                    if (rubySheepEntity != null) {
                        rubySheepEntity.setLocationAndAngles(
                                sheepEntity.getPosX(),
                                sheepEntity.getPosY(),
                                sheepEntity.getPosZ(),
                                sheepEntity.rotationYaw,
                                sheepEntity.rotationPitch
                        );

                        rubySheepEntity.onInitialSpawn(
                                playerIn.world,
                                playerIn.world.getDifficultyForLocation(rubySheepEntity.getPosition()),
                                SpawnReason.CONVERSION,
                                null,
                                null
                        );

                        playerIn.world.addEntity(rubySheepEntity);

                        sheepEntity.remove();
                    }
                }

                // func_233537_a_ -> resultSuccessClient()
                return ActionResultType.func_233537_a_(playerIn.world.isRemote);
            }
        }

        return ActionResultType.PASS;
    }
}
