package com.theonlytails.ruby.entities;

import com.theonlytails.ruby.registries.EntityTypeRegistry;
import com.theonlytails.ruby.registries.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;

public class RubySheepEntity extends SheepEntity {
    public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(ItemRegistry.RUBY.get(), Items.WHEAT);
    private EatGrassGoal eatGrassGoal;
    private int rubySheepTimer;

    public RubySheepEntity(EntityType<? extends SheepEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nonnull
    public static MutableAttribute setCustomAttributes() {
        // func_233666_p_() -> registerAttributes()
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10f)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25f));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0f));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0f, SheepEntity.class));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1f, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1f));
        this.goalSelector.addGoal(5, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0f));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(@Nonnull PlayerEntity player) {
        return super.getExperiencePoints(player);
    }

    @Nullable
    @Override
    public RubySheepEntity createChild(@Nonnull AgeableEntity ageable) {
        return EntityTypeRegistry.RUBY_SHEEP.get().create(world);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.1f, 1f);
    }

    @Override
    protected void updateAITasks() {
        this.rubySheepTimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public void livingTick() {
        if (this.world.isRemote) {
            rubySheepTimer = Math.max(0, rubySheepTimer - 1);
        }
        super.livingTick();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            rubySheepTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public void shear(@NotNull SoundCategory category) {
        this.world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, category, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.rand.nextInt(3);

        for (int j = 0; j < i; ++j) {
            ItemEntity itementity = this.entityDropItem(ItemRegistry.RUBY_WOOL_ITEM.get(), 1);
            if (itementity != null) {
                itementity.setMotion(itementity.getMotion().add((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F, this.rand.nextFloat() * 0.05F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F));
            }
        }
    }

    @NotNull
    @Override
    public List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable PlayerEntity player, @NotNull ItemStack item, @NotNull World world, @NotNull BlockPos pos, int fortune) {
        world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (!world.isRemote) {
            this.setSheared(true);
            int i = 1 + this.rand.nextInt(3);

            java.util.List<ItemStack> items = new java.util.ArrayList<>();
            for (int j = 0; j < i; ++j) {
                items.add(new ItemStack(ItemRegistry.RUBY_WOOL_ITEM.get()));
            }
            return items;
        }
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(@NotNull IWorld worldIn, @NotNull DifficultyInstance difficultyIn, @NotNull SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        ILivingEntityData onInitialSpawnResult = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setFleeceColor(DyeColor.WHITE);
        return onInitialSpawnResult;
    }

    @Override
    public @NotNull ResourceLocation getLootTable() {
        return new ResourceLocation("ruby", "entities/ruby_sheep");
    }

    @Override
    public boolean canMateWith(@NotNull AnimalEntity otherAnimal) {
        return otherAnimal instanceof SheepEntity && otherAnimal.isInLove();
    }
}
