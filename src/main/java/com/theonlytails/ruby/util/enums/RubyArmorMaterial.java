package com.theonlytails.ruby.util.enums;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.init.ItemsReg;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum RubyArmorMaterial implements IArmorMaterial {
    RUBY(TheOnlyTails.MOD_ID + ":ruby", 24,
            new int[]{2, 5, 6, 2},
            18, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            0f,
            () -> Ingredient.fromItems(ItemsReg.RUBY.get()), 0.5f);

    private static final int[] MAX_DAMAGE = {11, 16, 15, 13};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmount;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;
    private final float knockbackResistance;

    RubyArmorMaterial(String name, int maxDamageFactor,
                      int[] damageReductionAmount, int enchantability,
                      SoundEvent soundEvent, float toughness,
                      Supplier<Ingredient> repairMaterial, float knockbackResistance) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmount = damageReductionAmount;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
        this.knockbackResistance = knockbackResistance;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmount[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    @Nonnull
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    @Nonnull
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}