package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.entities.RubySheepEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheOnlyTails.MOD_ID);

    //  Entity Types
    public static final RegistryObject<EntityType<RubySheepEntity>> RUBY_SHEEP = ENTITY_TYPES.register("ruby_sheep", () -> EntityType.Builder.create(RubySheepEntity::new, EntityClassification.CREATURE).size(0.625f, 1.25f)
            .build(new ResourceLocation(TheOnlyTails.MOD_ID, "ruby_sheep").toString())
    );

    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
