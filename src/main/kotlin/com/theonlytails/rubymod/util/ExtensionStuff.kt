package com.theonlytails.rubymod.util

import net.minecraft.item.Food
import net.minecraft.potion.EffectInstance

fun Food.Builder.effect(probability: Float, effectIn: () -> EffectInstance) = this.effect(effectIn, probability)