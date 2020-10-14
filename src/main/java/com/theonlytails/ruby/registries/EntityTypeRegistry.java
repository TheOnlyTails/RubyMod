package com.theonlytails.ruby.registries;

import com.theonlytails.ruby.RubyMod;
import com.theonlytails.ruby.entities.RubySheepEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, RubyMod.MOD_ID);

    public static final RegistryObject<EntityType<RubySheepEntity>> RUBY_SHEEP = ENTITY_TYPES.register("ruby_sheep", () -> EntityType.Builder.create(RubySheepEntity::new, EntityClassification.CREATURE).size(0.625f, 1.25f)
            .build(new ResourceLocation(RubyMod.MOD_ID, "ruby_sheep").toString())
    );
}
