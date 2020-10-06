package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionsReg {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTION_TYPES, TheOnlyTails.MOD_ID);

    public static final RegistryObject<Potion> LAZINESS =
            POTIONS.register("laziness", () ->
                    new Potion("laziness",
                            new EffectInstance(Effects.SLOWNESS, 90 * 20),
                            new EffectInstance(Effects.NAUSEA, 90 * 20)
                    ));

    public static final RegistryObject<Potion> LONG_LAZINESS =
            POTIONS.register("long_laziness", () ->
                    new Potion("laziness",
                            new EffectInstance(Effects.SLOWNESS, 240 * 20),
                            new EffectInstance(Effects.NAUSEA, 240 * 20)));

    public static final RegistryObject<Potion> STRONG_LAZINESS =
            POTIONS.register("strong_laziness", () ->
                    new Potion("laziness",
                            new EffectInstance(Effects.SLOWNESS, 90 * 20, 1),
                            new EffectInstance(Effects.NAUSEA, 90 * 20, 1)
                    ));

    public static void init() {
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
