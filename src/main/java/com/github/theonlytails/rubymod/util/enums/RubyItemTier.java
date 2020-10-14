package com.github.theonlytails.rubymod.util.enums;

import com.github.theonlytails.rubymod.registries.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum RubyItemTier implements IItemTier {
    RUBY(3,
            850,
            7f,
            3f,
            12,
            () -> Ingredient.fromItems(ItemRegistry.RUBY.get()));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    RubyItemTier(int harvestLevel, int maxUses, float efficiency,
                 float attackDamage, int enchantability,
                 Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }


    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
