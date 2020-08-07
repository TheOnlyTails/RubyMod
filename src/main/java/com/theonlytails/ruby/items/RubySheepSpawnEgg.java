package com.theonlytails.ruby.items;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.theonlytails.ruby.init.RubyEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class RubySheepSpawnEgg extends Item {

    private static final Map<Supplier<EntityType<?>>, RubySheepSpawnEgg> EGGS = Maps.newIdentityHashMap();
    private final int primaryColor;
    private final int secondaryColor;
    private final Supplier<EntityType<?>> typeIn;

    public RubySheepSpawnEgg(Supplier<EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn,
                             Item.Properties builder) {
        super(builder);
        this.typeIn = typeIn;
        this.primaryColor = primaryColorIn;
        this.secondaryColor = secondaryColorIn;
        EGGS.put(typeIn, this);
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public static RubySheepSpawnEgg getEgg(@Nullable Supplier<EntityType<?>> type) {
        return EGGS.get(type);
    }

    public static Iterable<RubySheepSpawnEgg> getEggs() {
        return Iterables.unmodifiableIterable(EGGS.values());
    }

    @Override
    public @NotNull ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.isIn(Blocks.SPAWNER)) {
                TileEntity tileentity = world.getTileEntity(blockpos);
                if (tileentity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic();
                    EntityType<?> entitytype1 = this.getType(itemstack.getTag());
                    abstractspawner.setEntityType(entitytype1);
                    tileentity.markDirty();
                    world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
                    itemstack.shrink(1);
                    return ActionResultType.CONSUME;
                }
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            EntityType<?> entitytype = RubyEntityTypes.RUBY_SHEEP.get();
            if (entitytype.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true,
                    !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
                itemstack.shrink(1);
            }

            return ActionResultType.CONSUME;
        }
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        BlockRayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.resultPass(itemstack);
        } else if (worldIn.isRemote) {
            return ActionResult.resultSuccess(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.resultPass(itemstack);
            } else if (worldIn.isBlockModifiable(playerIn, blockpos)
                    && playerIn.canPlayerEdit(blockpos, raytraceresult.getFace(), itemstack)) {
                EntityType<?> entitytype = this.getType(itemstack.getTag());
                if (entitytype.spawn(worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false,
                        false) == null) {
                    return ActionResult.resultPass(itemstack);
                } else {
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    playerIn.addStat(Stats.ITEM_USED.get(this));
                    return ActionResult.resultConsume(itemstack);
                }
            } else {
                return ActionResult.resultFail(itemstack);
            }
        }
    }

    public boolean hasType(@Nullable CompoundNBT nbt, EntityType<?> type) {
        return Objects.equals(this.getType(nbt), type);
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    public EntityType<?> getType(@Nullable CompoundNBT nbt) {
        if (nbt != null && nbt.contains("EntityTag", 10)) {
            CompoundNBT compoundnbt = nbt.getCompound("EntityTag");
            if (compoundnbt.contains("id", 8)) {
                return EntityType.byKey(compoundnbt.getString("id")).orElse(this.typeIn.get());
            }
        }

        return this.typeIn.get();
    }

    public Optional<MobEntity> getMob(PlayerEntity player, MobEntity mob, EntityType<? extends MobEntity> mobType, World world, Vector3d position, ItemStack stack) {
        if (!this.hasType(stack.getTag(), mobType)) {
            return Optional.empty();
        } else {
            MobEntity mobentity;
            if (mob instanceof AgeableEntity) {
                mobentity = ((AgeableEntity) mob).createChild((AgeableEntity) mob);
            } else {
                mobentity = mobType.create(world);
            }

            if (mobentity == null) {
                return Optional.empty();
            } else {
                mobentity.setChild(true);
                if (!mobentity.isChild()) {
                    return Optional.empty();
                } else {
                    mobentity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), 0.0F, 0.0F);
                    world.addEntity(mobentity);
                    if (stack.hasDisplayName()) {
                        mobentity.setCustomName(stack.getDisplayName());
                    }

                    if (!player.abilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    return Optional.of(mobentity);
                }
            }
        }
    }
}