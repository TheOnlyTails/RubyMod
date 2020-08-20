package com.theonlytails.ruby.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PoisonedApple extends Item {
    public PoisonedApple() {
        super(new Item.Properties()
                .group(ItemGroup.FOOD)
                .food(new Food.Builder()
                        .hunger(7)
                        .saturation(1.2f)
                        // Gives you Nausea 2 for 7 seconds 100% of the time;
                        .effect(() -> new EffectInstance(Effects.NAUSEA, 7 * 20, 1), 1f)
                        // Gives you Poison 2 for 9 seconds 100% of the time;
                        .effect(() -> new EffectInstance(Effects.POISON, 9 * 20, 1), 1f)
                        // Gives you Glowing 1 for 10 seconds 100% of the time;
                        .effect(() -> new EffectInstance(Effects.GLOWING, 10 * 20, 0), 1f)
                        // Gives you Hunger 3 for 3 seconds 10% of the time;
                        .effect(() -> new EffectInstance(Effects.HUNGER, 3 * 20, 2), 0.1f)
                        // Gives you Blindness (!) 3 for 5 seconds 5% of the time;
                        .effect(() -> new EffectInstance(Effects.BLINDNESS, 5 * 20, 2), 0.05f)
                        // Gives you Luck (!) 1 for 1 seconds 50% of the time;
                        .effect(() -> new EffectInstance(Effects.LUCK, 20, 0), 0.5f)
                        // You can eat it even if you're not hungry;
                        .setAlwaysEdible()
                        .build())
        );
    }
}
