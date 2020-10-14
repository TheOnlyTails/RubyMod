package com.github.theonlytails.rubymod.registries;

import com.github.theonlytails.rubymod.RubyMod;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegistry {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, RubyMod.MOD_ID);

    public static final RegistryObject<Potion> MOTIVATION = POTIONS.register(
            "motivation",
            () -> new Potion(
                    "motivation",
                    new EffectInstance(Effects.SPEED, 90 * 20),
                    new EffectInstance(Effects.JUMP_BOOST, 90 * 20)
            )
    );

    public static final RegistryObject<Potion> LONG_MOTIVATION = POTIONS.register(
            "long_motivation",
            () -> new Potion(
                    "motivation",
                    new EffectInstance(Effects.SPEED, 240 * 20),
                    new EffectInstance(Effects.JUMP_BOOST, 240 * 20)
            )
    );

    public static final RegistryObject<Potion> STRONG_MOTIVATION = POTIONS.register(
            "strong_motivation",
            () -> new Potion(
                    "motivation",
                    new EffectInstance(Effects.SPEED, 90 * 20, 1),
                    new EffectInstance(Effects.JUMP_BOOST, 90 * 20, 1)
            )
    );

    public static final RegistryObject<Potion> LAZINESS = POTIONS.register(
            "laziness",
            () -> new Potion(
                    "laziness",
                    new EffectInstance(Effects.SLOWNESS, 90 * 20),
                    new EffectInstance(Effects.NAUSEA, 90 * 20)
            )
    );

    public static final RegistryObject<Potion> LONG_LAZINESS = POTIONS.register(
            "long_laziness",
            () -> new Potion(
                    "laziness",
                    new EffectInstance(Effects.SLOWNESS, 240 * 20),
                    new EffectInstance(Effects.NAUSEA, 240 * 20)
            )
    );

    public static final RegistryObject<Potion> STRONG_LAZINESS = POTIONS.register(
            "strong_laziness",
            () -> new Potion(
                    "laziness",
                    new EffectInstance(Effects.SLOWNESS, 90 * 20, 1),
                    new EffectInstance(Effects.NAUSEA, 90 * 20, 1)
            )
    );
}
